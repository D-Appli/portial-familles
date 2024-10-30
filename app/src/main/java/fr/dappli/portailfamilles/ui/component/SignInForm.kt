package fr.dappli.portailfamilles.ui.component

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SignInForm() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                    webViewClient = SiteWebViewClient()
                    with(settings) {
                        loadsImagesAutomatically = true
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        textZoom = 100
                    }
                    addJavascriptInterface(WebCallback(), PLATFORM)
                }
            },
            update = { webView ->
                webView.loadUrl("https://deuillabarre.portail-familles.com/login")
            }
        )
    }
}

private class SiteWebViewClient : WebViewClient() {

    private var isJsAdded: Boolean = false

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        if (isJsAdded.not()) {
            isJsAdded = true
            // JavaScript to get all localStorage items
            val jsCode = """
                    (function() {
                        function notifyUrlChange() {
                            Android.onUrlChanged(window.location.href, localStorage.getItem("token"));
                        }

                        // Monitor URL changes via popstate (history navigation)
                        window.addEventListener('popstate', notifyUrlChange);

                        // Monitor URL changes via pushState or replaceState
                        (function(history) {
                            const originalPushState = history.pushState;
                            const originalReplaceState = history.replaceState;

                            history.pushState = function() {
                                originalPushState.apply(history, arguments);
                                notifyUrlChange();
                            };

                            history.replaceState = function() {
                                originalReplaceState.apply(history, arguments);
                                notifyUrlChange();
                            };
                        })(window.history);
                    })();
                """.trimIndent()

            view!!.evaluateJavascript(jsCode, null)
        }
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?,
    ) {
        super.onReceivedError(view, request, error)
        println("andrei webview client error ${error?.errorCode} ${error?.description}")
    }
}

private class WebCallback() {

    @JavascriptInterface
    @Keep
    fun onUrlChanged(newUrl: String, token: String?) {
        println("andrei newUrl: $newUrl, token=$token")
    }
}

private const val PLATFORM = "Android"
