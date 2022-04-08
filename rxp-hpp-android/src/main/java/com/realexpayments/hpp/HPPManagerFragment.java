package com.realexpayments.hpp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import static com.realexpayments.hpp.Constants.HTML_MIME_TYPE;
import static com.realexpayments.hpp.Constants.HTTP_SCHEME_ENDING;
import static com.realexpayments.hpp.Constants.JS_WEBVIEW_OBJECT_NAME;
import static com.realexpayments.hpp.Constants.SLASH;
import static com.realexpayments.hpp.Constants.UTF_8;
import static com.realexpayments.hpp.HPPResponse.HPP_POST_RESPONSE;
import static com.realexpayments.hpp.HPPResponse.HPP_VERSION;

/**
 * Payment form fragment.
 * <p>
 * Insert the HppManager fragment into your activity as follows;
 * Fragment hppManagerFragment = hppManager.newInstance();
 * getFragmentManager() .beginTransaction().add(R.id.container,hppManagerFrament) .commit();
 **/

public class HPPManagerFragment extends Fragment implements Callback<Response> {

    private HPPManagerListener mListener;
    private View root;
    private HPPManager hppManager;
    private boolean isResultReceived = false;
    private Response hppResponseTry;
    private Response responseTry;

    public HPPManagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hppManager = HPPManager.createFromBundle(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.hppmanager_fragment, container, false);
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (HPPManagerListener) activity;
            if (getArguments() != null) {
                hppManager = HPPManager.createFromBundle(getArguments());
                HashMap<String, String> parameters = hppManager.getMap();
                ApiAdapter.getAdapter(
                        getHostPath(hppManager.getHppRequestProducerURL()),
                        getRequestHeaders())
                        .getHPPRequest(
                                getRelativePathEncoded(hppManager.getHppRequestProducerURL()),
                                parameters,
                                this);
            } else {
                onError(new HPPError("Invalid arguments", null));
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity
                    + " must implement HPPManagerListener");
        }
    }

    private Map<String, String> getRequestHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        HashMap<String, String> additionalHeaders = hppManager.getAdditionalHeaders();
        if (additionalHeaders != null && !additionalHeaders.isEmpty()) {
            for (String headerName : additionalHeaders.keySet()) {
                String headerValue = additionalHeaders.get(headerName);

                if (!TextUtils.isEmpty(headerName) && !TextUtils.isEmpty(headerValue)) {
                    headersMap.put(headerName, headerValue);
                }
            }
        }
        return headersMap;
    }

    private String getHostPath(String urlString) {
        return urlString.substring(0, urlString.lastIndexOf(getRelativePath(urlString)) - 1);
    }

    private String getRelativePath(String urlString) {
        Uri uri = Uri.parse(urlString);
        String path = uri.getPath();
        return (path.startsWith(SLASH)) ? path.substring(1) : path;
    }

    private String getRelativePathEncoded(String urlString) {
        Uri uri = Uri.parse(urlString);
        String encodedPath = uri.getEncodedPath();
        return (encodedPath.startsWith(SLASH)) ? encodedPath.substring(1) : encodedPath;
    }

    @Override
    public void onDestroy() {
        if (!isResultReceived) {
            mListener.hppManagerCancelled();
        }
        mListener = null;
        super.onDestroy();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void success(Response hppResponse, Response response) {
        hppResponseTry = hppResponse;
        responseTry = response;
        final WebView webView = root.findViewById(R.id.hpp_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, JS_WEBVIEW_OBJECT_NAME);
        WebView.setWebContentsDebuggingEnabled(true);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    if (!isResultReceived) {
                        webView.goBack();
                        mListener.hppManagerCancelled();
                        isResultReceived = true;
                    }
                    return false;
                }
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage.message().startsWith(HPPManager.RESULT_MESSAGE)) {
                    String msg = consoleMessage.message().substring(HPPManager.RESULT_MESSAGE.length());
                    callbackHandler(msg, hppManager.getHppURL());
                    return true;
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

                                     Handler handler = new Handler();
                                     String url;

                                     @TargetApi(Build.VERSION_CODES.KITKAT)
                                     @Override
                                     public void onLoadResource(final WebView view, String url) {
                                         this.url = url;
                                         if (url.endsWith("api/auth")) {
                                             checkResult(view);
                                         }
                                         super.onLoadResource(view, url);
                                     }

                                     private void checkResult(final WebView view) {
                                         handler.postDelayed(new Runnable() {
                                             @Override
                                             public void run() {
                                                 view.evaluateJavascript("javascript:console.log('" + HPPManager.RESULT_MESSAGE + "'+document.getElementById('result-message').innerHTML);", new ValueCallback<String>() {
                                                     @Override
                                                     public void onReceiveValue(String value) {
                                                         if (!isResultReceived) {
                                                             checkResult(view);
                                                         }
                                                     }
                                                 });
                                             }
                                         }, 100);
                                     }

                                     @SuppressLint("NewApi")
                                     @Override
                                     public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                                         super.onReceivedHttpError(view, request, errorResponse);
                                         if (this.url.equals(hppManager.getHppURL())) {
                                             isResultReceived = true;
                                             onError(new HPPError(errorResponse.getReasonPhrase(), hppManager.getHppURL()));
                                         }
                                     }

                                     @SuppressLint("NewApi")
                                     @Override
                                     public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                         super.onReceivedError(view, request, error);
                                         isResultReceived = true;
                                         onError(new HPPError(error.getDescription().toString(), hppManager.getHppURL()));
                                     }

                                     @Override
                                     public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                         super.onReceivedSslError(view, handler, error);
                                         isResultReceived = true;
                                         onError(new HPPError(error.toString(), hppManager.getHppURL()));
                                     }
                                 }
        );
        String resp = new String(((TypedByteArray) response.getBody()).getBytes());
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> consumerResponseParams = new Gson().fromJson(resp, mapType);
        if (resp.isEmpty()) {
            success(hppResponse, response);
        } else {
            postHPPData(webView, getHPPPostData(consumerResponseParams));
        }
    }

    private HashMap<String, String> getHPPPostData(Map<String, String> params) {
        HashMap<String, String> map = hppManager.getMap();

        // default to HPP Version 2
        map.put(HPP_VERSION, "2");

        // determine the target origin to receive the response
        Uri uri = Uri.parse(hppManager.getHppRequestProducerURL());
        String hppPostResponse = uri.getScheme() + HTTP_SCHEME_ENDING + uri.getHost();
        map.put(HPP_POST_RESPONSE, hppPostResponse);

        for (String key : params.keySet()) {
            String paramValue = params.get(key);

            if (!TextUtils.isEmpty(paramValue)) {
                if (HPPManager.isEncoded()) {
                    byte[] decodedValue = Base64.decode(paramValue, Base64.DEFAULT);
                    String decodedString = new String(decodedValue);
                    map.put(key, decodedString);
                } else {
                    map.put(key, paramValue);
                }

            }
        }
        return map;
    }

    private void postHPPData(final WebView webView, HashMap<String, String> postData) {
        ApiAdapter.getAdapter(getHostPath(hppManager.getHppURL()), getRequestHeaders())
                .getHPP(getRelativePathEncoded(hppManager.getHppURL()), postData,
                        new Callback<Response>() {
                            @Override
                            public void success(Response s, Response response) {
                                String htmlString = new String(((TypedByteArray) response.getBody()).getBytes());
                                loadWebView(webView, hppManager.getHppURL(), htmlString);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                onError(new HPPError(error.getMessage(), error, error.getUrl()));
                            }
                        }
                );
    }

    private void loadWebView(final WebView webView, final String url, final String htmlString) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.clearCache(true);
                webView.loadDataWithBaseURL(url, htmlString, HTML_MIME_TYPE, UTF_8, null);
            }
        });
    }

    @JavascriptInterface
    public void callbackHandler(String data, String url) {
        if (!isResultReceived && data.length() > 0) {
            isResultReceived = true;
            ApiAdapter.getAdapter(getHostPath(hppManager.getHppResponseConsumerURL()), getRequestHeaders())
                    .getConsumerRequest(
                            getRelativePathEncoded(hppManager.getHppResponseConsumerURL()),
                            data,
                            new Callback<Response>() {
                                @Override
                                public void success(Response s, Response response) {
                                    String msg = new String(((TypedByteArray) response.getBody()).getBytes());
                                    Method[] methods = mListener.getClass().getDeclaredMethods();
                                    for (int i = 0; i < methods.length; i++) {
                                        if (methods[i].getName().equals(HPPManagerListener.HPP_MANAGER_COMPLETED_WITH_RESULT)) {
                                            Method method = methods[i];
                                            try {
                                                onSuccess(ApiAdapter.getGson().fromJson(msg, method.getParameterTypes()[0]));
                                            } catch (Exception error) {
                                                onError(new HPPError(msg, error, hppManager.getHppResponseConsumerURL()));
                                            }
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    onError(new HPPError(error.getMessage(), error, error.getUrl()));
                                }
                            }
                    );
        }
    }

    @Override
    public void failure(RetrofitError error) {
        onError(new HPPError(error.getMessage(), error, error.getUrl()));
    }

    public void onSuccess(Object result) {
        if (mListener != null) {
            mListener.hppManagerCompletedWithResult(result);
        }
    }

    public void onError(HPPError hppError) {
        if (mListener != null) {
            mListener.hppManagerFailedWithError(hppError);
        }
    }

}
