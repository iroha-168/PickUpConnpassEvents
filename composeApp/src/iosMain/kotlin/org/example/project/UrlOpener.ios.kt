package org.example.project

import platform.AuthenticationServices.ASWebAuthenticationPresentationContextProvidingProtocol
import platform.AuthenticationServices.ASWebAuthenticationSession
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.darwin.NSObject

actual fun openUrl(url: String, context: Any?) {
    val nsUrl = NSURL.URLWithString(url) ?: return
    val session = ASWebAuthenticationSession(
        uRL = nsUrl,
        callbackURLScheme = null,
        completionHandler = { _, _ -> },
    )
    session.presentationContextProvider = object : NSObject(),
        ASWebAuthenticationPresentationContextProvidingProtocol {
            override fun presentationAnchorForWebAuthenticationSession(
                session: ASWebAuthenticationSession
            ): UIWindow {
                return UIApplication.sharedApplication.keyWindow!!
            }
        }
    session.start()
}