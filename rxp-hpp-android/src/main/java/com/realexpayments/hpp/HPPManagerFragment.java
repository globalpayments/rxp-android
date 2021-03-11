package com.realexpayments.hpp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

import androidx.annotation.NonNull;
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
import static com.realexpayments.hpp.Constants.UTF_8;
import static com.realexpayments.hpp.HPPResponse.HPP_POST_RESPONSE;
import static com.realexpayments.hpp.HPPResponse.HPP_VERSION;
import static com.realexpayments.hpp.Utils.collectValidMapValues;
import static com.realexpayments.hpp.Utils.decode;
import static com.realexpayments.hpp.Utils.getHostPath;
import static com.realexpayments.hpp.Utils.getRelativePathEncoded;

/**
 * Payment form fragment.
 * <p>
 * Insert the HppManager fragment into your activity as follows;
 * Fragment hppManagerFragment = hppManager.newInstance();
 * getFragmentManager() .beginTransaction().add(R.id.container,hppManagerFrament) .commit();
 **/

public class HPPManagerFragment extends Fragment {
    private HPPManagerListener mListener;
    private HPPManager hppManager;
    private boolean isResultReceived = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hppmanager_fragment, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (HPPManagerListener) context;
            Bundle arguments = getArguments();

            if (arguments == null) {
                mListener.hppManagerFailedWithError(new HPPError("Invalid arguments", null));
            } else {
                hppManager = HPPManager.createFromBundle(arguments);
                getHPPRequest();
            }

        } catch (ClassCastException e) {
            throw new ClassCastException(requireActivity().toString()
                    + " must implement HPPManagerListener");
        }
    }

    private void getHPPRequest() {
        String hppRequestProducerURL = hppManager.getHppRequestProducerURL();

        ApiAdapter.getAdapter(getHostPath(hppRequestProducerURL), getRequestHeaders())
                .getHPPRequest(
                        getRelativePathEncoded(hppRequestProducerURL),
                        hppManager.getMap(),
                        new Callback<Response>() {
                            @Override
                            public void success(Response hppResponse, Response response) {
                                String resp = new String(((TypedByteArray) response.getBody()).getBytes());
                                Type mapType = new TypeToken<Map<String, String>>() {
                                }.getType();
                                Map<String, String> consumerResponseParams = new Gson().fromJson(resp, mapType);
                                postHPPData(buildWebView(), getHPPPostData(consumerResponseParams));
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                HPPError hppError = new HPPError(error.getMessage(), error, error.getUrl());
                                mListener.hppManagerFailedWithError(hppError);
                            }
                        }
                );
    }

    private Map<String, String> getRequestHeaders() {
        return collectValidMapValues(hppManager.getAdditionalHeaders());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private WebView buildWebView() {
        final WebView webView = requireView().findViewById(R.id.hpp_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, JS_WEBVIEW_OBJECT_NAME);
        WebView.setWebContentsDebuggingEnabled(true);

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
                    return true;
                }

                return super.onConsoleMessage(consoleMessage);
            }
        });

        webView.setWebViewClient(buildWebViewClient());
        return webView;
    }

    private WebViewClient buildWebViewClient() {
        return new WebViewClient() {
            Handler handler = new Handler();
            String url;

            @Override
            public void onLoadResource(final WebView view, String url) {
                this.url = url;
                if (url.endsWith("api/auth")) {
                    checkResult(view, handler);
                }

                super.onLoadResource(view, url);
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (this.url.equals(hppManager.getHppURL())) {
                    isResultReceived = true;
                    HPPError hppError = new HPPError(errorResponse.getReasonPhrase(), hppManager.getHppURL());
                    mListener.hppManagerFailedWithError(hppError);
                }
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isResultReceived = true;
                HPPError hppError = new HPPError(error.getDescription().toString(), hppManager.getHppURL());
                mListener.hppManagerFailedWithError(hppError);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                isResultReceived = true;
                HPPError hppError = new HPPError(error.toString(), hppManager.getHppURL());
                mListener.hppManagerFailedWithError(hppError);
            }
        };
    }

    private void checkResult(final WebView view, final Handler handler) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String script = "javascript:console.log('" + HPPManager.RESULT_MESSAGE
                        + "'+document.getElementById('result-message').innerHTML);";

                view.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        if (!isResultReceived) {
                            checkResult(view, handler);
                        }
                    }
                });

            }
        }, 100);
    }

    private HashMap<String, String> getHPPPostData(Map<String, String> params) {
        HashMap<String, String> map = new HashMap<>();

        // default to HPP Version 2
        map.put(HPP_VERSION, "2");

        // determine the target origin to receive the response
        Uri uri = Uri.parse(hppManager.getHppRequestProducerURL());
        String hppPostResponse = uri.getScheme() + HTTP_SCHEME_ENDING + uri.getHost();
        map.put(HPP_POST_RESPONSE, hppPostResponse);

        for (String key : params.keySet()) {
            String paramValue = params.get(key);

            if (!TextUtils.isEmpty(paramValue)) {
                String finalParamValue = HPPManager.isEncoded() ? decode(paramValue) : paramValue;
                map.put(key, finalParamValue);
            }
        }

        return map;
    }

    private void postHPPData(final WebView webView, HashMap<String, String> postData) {
        final String hppURL = hppManager.getHppURL();

        ApiAdapter.getAdapter(getHostPath(hppURL), getRequestHeaders())
                .getHPP(getRelativePathEncoded(hppURL), postData,
                        new Callback<Response>() {
                            @Override
                            public void success(Response s, Response response) {
                                String htmlString = new String(((TypedByteArray) response.getBody()).getBytes());
                                loadWebView(webView, hppURL, htmlString);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                HPPError hppError = new HPPError(error.getMessage(), error, error.getUrl());
                                mListener.hppManagerFailedWithError(hppError);
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
            String hppResponseConsumerURL = hppManager.getHppResponseConsumerURL();

            ApiAdapter.getAdapter(getHostPath(hppResponseConsumerURL), getRequestHeaders())
                    .getConsumerRequest(
                            getRelativePathEncoded(hppResponseConsumerURL),
                            data,
                            new Callback<Response>() {
                                @Override
                                public void success(Response s, Response response) {
                                    String msg = new String(((TypedByteArray) response.getBody()).getBytes());
                                    handleConsumerResponse(msg);
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    HPPError hppError = new HPPError(error.getMessage(), error, error.getUrl());
                                    mListener.hppManagerFailedWithError(hppError);
                                }
                            }
                    );

        }
    }

    private void handleConsumerResponse(String msg) {
        Method[] methods = mListener.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals(HPPManagerListener.HPP_MANAGER_COMPLETED_WITH_RESULT)) {
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
    public void onDestroy() {
        if (!isResultReceived) {
            mListener.hppManagerCancelled();
            isResultReceived = true;
        }

        mListener = null;
        super.onDestroy();
    }

}
