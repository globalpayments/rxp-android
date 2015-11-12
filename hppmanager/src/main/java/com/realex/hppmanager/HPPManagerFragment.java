package com.realex.hppmanager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class HPPManagerFragment extends Fragment implements Callback<HppResponse> {

    private HPPManagerListener mListener;
    private View root;
    private HPPManager hppManager;
    private boolean isResultReceived = false;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

                ApiAdapter.getAdapter(getHostPath(hppManager.getHppRequestProducerURL())).getHPPRequest(getRelativePathEncoded(hppManager.getHppRequestProducerURL()), parameters, this);

            } else {

                mListener.hppManagerFailedWithError(new HPPError("Invalid arguments", null));
            }

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement HPPManagerListener");
        }
    }

    private String getHostPath(String urlString) {
        String host = urlString.substring(0, urlString.indexOf(getRelativePath(urlString)) - 1);
        return host;
    }

    private String getRelativePath(String urlString) {

        Uri uri = Uri.parse(urlString);
        String path = (uri.getPath().startsWith("/")) ? uri.getPath().substring(1) : uri.getPath();
        return path;
    }

    private String getRelativePathEncoded(String urlString) {

        Uri uri = Uri.parse(urlString);
        String path = (uri.getEncodedPath().startsWith("/")) ? uri.getEncodedPath().substring(1) : uri.getEncodedPath();
        return path;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!isResultReceived) {
            mListener.hppManagerCancelled();
            isResultReceived = true;
        }
        mListener = null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void success(HppResponse hppResponse, Response response) {

        final WebView webView = (WebView) root.findViewById(R.id.hpp_web_view);

        webView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    if (!isResultReceived) {
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

                    Log.d("TEST", msg);

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

                                     @Override
                                     public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                                         super.onReceivedHttpError(view, request, errorResponse);

                                         if (this.url.equals(hppManager.getHppURL())) {

                                             isResultReceived = true;
                                             mListener.hppManagerFailedWithError(new HPPError(errorResponse.getReasonPhrase(), hppManager.getHppURL()));
                                         }

                                     }

                                     @Override
                                     public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                                         super.onReceivedError(view, request, error);

                                         isResultReceived = true;
                                         mListener.hppManagerFailedWithError(new HPPError(error.getDescription().toString(), hppManager.getHppURL()));

                                     }

                                     @Override
                                     public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                         super.onReceivedSslError(view, handler, error);
                                         isResultReceived = true;
                                         mListener.hppManagerFailedWithError(new HPPError(error.toString(), hppManager.getHppURL()));

                                     }
                                 }
        );

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebContentsDebuggingEnabled(true);

        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

        HashMap<String, String> params = hppManager.getMap();

        HashMap<String, String> consumer_response_params = hppResponse.getMap();

        //merge params
        for (String key : consumer_response_params.keySet()) {
            params.put(key, consumer_response_params.get(key));
        }

        if (hppManager.isLightBox()) {
            params.put(HPPManager.HPP_TEMPLATE_TYPE, "LIGHTBOX");
            Uri uri = Uri.parse(hppManager.getHppRequestProducerURL());
            params.put(HPPManager.HPP_ORIGIN, uri.getScheme() + "://" + uri.getHost());
        }

        for (String key : params.keySet()) {
            if (params.get(key) != null && params.get(key).length() > 0)
                nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {

            String postData = null;

            if (hppManager.isLightBox()) {
                postData = format(nvps, true)
                        + QP_SEP_A + "MERCHANT_RESPONSE_URL" + NAME_VALUE_SEPARATOR + Base64.encodeToString(
                        hppManager.getHppResponseConsumerURL().getBytes(), 0);
            } else {
                nvps.add(new BasicNameValuePair("MERCHANT_RESPONSE_URL", hppManager.getHppResponseConsumerURL()));
                postData = format(nvps, true);
            }

            webView.addJavascriptInterface(this, "HppManager");

            Log.d("", hppManager.getHppURL());
            Log.d("", postData);

            webView.postUrl(hppManager.getHppURL(), postData.getBytes());

        } catch (UnsupportedEncodingException e) {
        }

    }

    @JavascriptInterface
    public void callbackHandler(String data, String url) {
        if (!isResultReceived && data.length() > 0) {
            isResultReceived = true;

            ApiAdapter.getAdapter(getHostPath(hppManager.getHppResponseConsumerURL())).getConsumerRequest(getRelativePathEncoded(hppManager.getHppResponseConsumerURL()),
                    data, new Callback<Response>() {
                        @Override
                        public void success(Response s, Response response) {

                            String msg = new String(((TypedByteArray) response.getBody()).getBytes());

                            Method[] methods = mListener.getClass().getDeclaredMethods();

                            for (int i = 0; i < methods.length; i++) {
                                if (methods[i].getName().equals(HPPManagerListener.HPP_MANAGER_COMPLETED_WITH_RESULT)) {
                                    Method method = methods[i];

                                    try {
                                        mListener.hppManagerCompletedWithResult(ApiAdapter.getGson().fromJson(msg, method.getParameterTypes()[0]));
                                    } catch (Exception error) {
                                        mListener.hppManagerFailedWithError(new HPPError(msg, error, hppManager.getHppResponseConsumerURL()));
                                    }
                                    break;
                                }
                            }

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            mListener.hppManagerFailedWithError(new HPPError(error.getMessage(), error, error.getUrl()));
                        }
                    });

        }
    }

    private static final char QP_SEP_A = '&';
    private static final String NAME_VALUE_SEPARATOR = "=";

    private String format(List<BasicNameValuePair> parameters, boolean encode) throws UnsupportedEncodingException {
        final StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : parameters) {
            final String encodedName = encode ? URLEncoder.encode(parameter.getName(), "UTF-8") : parameter.getName();
            final String encodedValue = encode ? URLEncoder.encode(parameter.getValue(), "UTF-8") : parameter.getValue();
            if (result.length() > 0) {
                result.append(QP_SEP_A);
            }
            result.append(encodedName);
            if (encodedValue != null) {
                result.append(NAME_VALUE_SEPARATOR);
                result.append(encodedValue);
            }
        }
        return result.toString();
    }

    @Override
    public void failure(RetrofitError error) {

        mListener.hppManagerFailedWithError(new HPPError(error.getMessage(), error, error.getUrl()));
    }

}
