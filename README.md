# Weibo Android SDK

[![Join the chat at https://gitter.im/8tory/weibo-android-sdk](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/8tory/weibo-android-sdk?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Build Status](https://travis-ci.org/8tory/weibo-android-sdk.svg)](https://travis-ci.org/8tory/weibo-android-sdk)

![](art/weibo-android-sdk.png)

Used to integrate Android apps with Weibo Platform.

## Installation

via jcenter:

```java
repositories {
    jcenter()
}

dependencies {
    compile 'com.infstory:weibo-android-sdk:3.1.1'
}
```

or, via jitpack:

```java
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    compile 'com.github.8tory:weibo-android-sdk:-SNAPSHOT'
}
```

## Usage

* getFriendsTimeline, `/statuses/friends_timeline.json`:

Using Weibo Core SDK:

```java
WeiboParameters params = new WeiboParameters(appId);
params.put("access_token", accessToken); // AbsOpenAPI.KEY_ACCESS_TOKEN
// put ...

new AsyncWeiboRunner(context).requestAsync(
  "https://api.weibo.com/2" + "/statuses/friends_timeline.json", // AbsOpenAPI.API_SERVER
  params,
  "GET", // AbsOpenAPI.HTTPMETHOD_GET
  new RequestListener() {
    @Override public void onComplete(String json) {
    }
    @Override public void onWeiboException(WeiboException e) {
    }
  }
);
```

Using Weibo SDK:

```java
StatusesAPI statusesApi = new StatusesAPI(context, appId, accessToken);
statusesApi.friendsTimeline(0L, 0L, 10, 1, false, 0, false, new RequestListener() {
    @Override public void onComplete(String json) {
        StatusList statusList = StatusList.parse(response);
        List<Status> statuses = statusList.statusList;
        // statusAdapter.addAll(statuses);
        // statusAdapter.notifyDataSetChanged();
    }
    @Override public void onWeiboException(WeiboException e) {
    }
  }
);
```

or using Weibo SDK with StatusList.RequestListener:

```java
StatusesAPI statusesApi = new StatusesAPI(context, appId, accessToken);
statusesApi.friendsTimeline(0L, 0L, 10, 1, false, 0, false, new StatusList.RequestListener() {
    @Override public void onComplete(List<Status> statuses) {
        // statusAdapter.addAll(statuses);
        // statusAdapter.notifyDataSetChanged();
    }
    @Override public void onWeiboException(WeiboException e) {
    }
  }
);
```

## Weibo Core SDK of javadoc

```java
public class com.sina.weibo.sdk.net.WeiboParameters {
  public com.sina.weibo.sdk.net.WeiboParameters(java.lang.String);
  public java.lang.String getAppKey();
  public java.util.LinkedHashMap<java.lang.String, java.lang.Object> getParams();
  public void setParams(java.util.LinkedHashMap<java.lang.String, java.lang.Object>);
  public void add(java.lang.String, java.lang.String);
  public void add(java.lang.String, int);
  public void add(java.lang.String, long);
  public void add(java.lang.String, java.lang.Object);
  public void put(java.lang.String, java.lang.String);
  public void put(java.lang.String, int);
  public void put(java.lang.String, long);
  public void put(java.lang.String, android.graphics.Bitmap);
  public void put(java.lang.String, java.lang.Object);
  public java.lang.Object get(java.lang.String);
  public void remove(java.lang.String);
  public java.util.Set<java.lang.String> keySet();
  public boolean containsKey(java.lang.String);
  public boolean containsValue(java.lang.String);
  public int size();
  public java.lang.String encodeUrl();
  public boolean hasBinaryData();
}
```

```java
public class com.sina.weibo.sdk.auth.Oauth2AccessToken {
  public static final java.lang.String KEY_UID;
  public static final java.lang.String KEY_ACCESS_TOKEN;
  public static final java.lang.String KEY_EXPIRES_IN;
  public static final java.lang.String KEY_REFRESH_TOKEN;
  public static final java.lang.String KEY_PHONE_NUM;
  public com.sina.weibo.sdk.auth.Oauth2AccessToken();
  public com.sina.weibo.sdk.auth.Oauth2AccessToken(java.lang.String);
  public com.sina.weibo.sdk.auth.Oauth2AccessToken(java.lang.String, java.lang.String);
  public static com.sina.weibo.sdk.auth.Oauth2AccessToken parseAccessToken(java.lang.String);
  public static com.sina.weibo.sdk.auth.Oauth2AccessToken parseAccessToken(android.os.Bundle);
  public boolean isSessionValid();
  public android.os.Bundle toBundle();
  public java.lang.String toString();
  public java.lang.String getUid();
  public void setUid(java.lang.String);
  public java.lang.String getToken();
  public void setToken(java.lang.String);
  public java.lang.String getRefreshToken();
  public void setRefreshToken(java.lang.String);
  public long getExpiresTime();
  public void setExpiresTime(long);
  public void setExpiresIn(java.lang.String);
  public java.lang.String getPhoneNum();
}
```

## How to start

Steps:

* Step 1: README
* Step 2: Demo Application: [WeiboSDKDemo.apk][4]
* Step 3: Documentation: [Weibo-Android-SDK-v3.1.1.cn.pdf][1]
* Step 4: [Demo Application source code][5]

See Also:

* [FAQ][2]
* [JavaDoc][8]

------

## Release-Note: Android SDK V3.1.1

### Changelog:

1. Authorization Performance fine tune
2. Sharing Webpage fine tune
3. Add social of comment feaature
4. Add social of following feaature
5. Add social of weibo-pay feaature
6. Add SMS Authorization

------

## Quick Start

### Overview

Details: [Weibo-Android-SDK-v3.1.1.cn.pdf][1]

------

## Dictionary

| noun        | Explain    |
| --------    | :-----     |
| AppKey      | Identify for application |
| RedirectURI | 3rd-party application's redirect page. Default: `https://api.weibo.com/oauth2/default.html` |
| Scope       | Allow more core features for developer via specific scope. To protect user privacy and user experiences. Using OAuth 2.0 authroization allows selectivity permissions |
| AccessToken | Identify for user permissions |
| Web Auth    | WebView authentication returns token |
| SSO Auth    | Weibo-App authentication returns token |

------

## Features

### 1. Authentication

 - SSO Auth: Using SSO authentication. Require Weibo-app installed.
 - Web Auth: Using Web authentication.
 - SSO Auth + Web Auth of hybrid-authentication: (**Suggested**) SSO Auth if Weibo-app installed or Web Auth. Details: `WBAuthActivity` in Demo

### 2. Weibo Sharing

Allow 3rd-party application to share text, image, video, music, etc. There are sharing ways:

**With Weibo-app**

* Using Weibo-app sharing via 3rd-party application intents (For 3rd-party application)
* Using 3rd-party application via Weibo-app intents (Details: http://t.cn/aex4JF)

**Without Weibo-app**

* Using `OpenAPI` to share. Using `upload` or `update` or `uploadUrlText` of `StatusesAPI` to share, or directly using `AsyncWeiboRunner#requestAsync` with HTTP request implementation. Details: [Using asynchronized API with one attachment of image][7]。

### 3. Login/Logout Buttons

Weibo SDK provides two buttons, login and login/logout. Both of buttons are using SSO authentication.

### 4. Open API

Weibo SDK provides a simple OpenAPI framework, and encapsulated some APIs, please refer OpenAPI: http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI

| API type       | Explain    |
| --------    | :-----  |
| UsersAPI    | Fetch user Information|
| StatusesAPI | Fetch posts |
| CommentsAPI | Fetch comments |
| LogoutAPI   | Revoke permissions|
| InviteAPI   | Allow invite friends and gifts |

**NOTICE**: We implemented only a part of APIs, because APIs are too huge. For each API implementation and testing was spent a lot of time. So we have priority in APIs implementations such as above tables. In fact, we welcome the pull-request of API implementation on Github. We already simplized endpoints, allow 3rd-party friendly to use OpenAPI via HTTP request. Details: [Weibo-RESTful-framework][6]

------

## JavaDoc

* http://sinaweibosdk.github.io/weibo_android_sdk/doc

------

## Demonstration

For 3rd-party integration of Weibo SDK. We have a demonstration with apk and source code.

1. Using `adb install` command to install [WeiboSDKDemo.apk][4]
2. For Eclipse, import WeiboSDKDemo project.(Details: [Weibo-Android-SDK-v3.1.1.cn.pdf][1])
3. For Android Studio, import WeiboSDKDemo project. (Details: [Weibo-Android-SDK-v3.1.1.cn.pdf][1])

**NOTICE**: Please replace default debug.keystore or occurs incorrect authentiation. The debug.keysotre is sina official provided. DO NOT compile anthor application with it. Based on security, you should use custom keystore.


## Directory Structure of WeiboSDK and Demonstration

```
native weibocore so <- weibocore jar (restful framemwork) <- Weibo SDK for android (Weibo Android SDK OpenAPI)
```

WeiboSDK now is open partial source for 3rd-party developer. In short, There are three parts:

* **Proprietary**: This `weibosdkcore.jar` includes Weibo authentication, SSO login, sharing, etc. core features. For **V2.5.0+**, Weibo provides RESTful framework for OpenAPI development.
* **Open Source**: WeiboSDK project(Library) is depended on `weibosdkcore.jar` and simplized with Java API. We welcome 3rd-party to add more API via pull-requests. Using OpenAPI to fetch user information or Weibo-sharing, SSO, etc.
* **Demonstration**: `WeiboSDKDemo` is depended on WeiboSDK, provides demonstraction of all features and sample code.

------

## Prepare for integration

### 1. Apply for Weibo application of APP_KEY

* APP_KEY and (Redirect URI). Details: http://t.cn/aex4JF for mobile.

### 2. Register package name and signature

package name: In `AndroidManifest.xml`, `package` attributed.

package signature: MD5 signature generated by Weibo official sign tool.

Details: In **How to generate signature with Weibo official sign tool** chapter ,[Weibo-Android-SDK-v3.1.1.cn.pdf][1]

### 3. Choose integration

There are two ways to integration of WeiboSDK:

1. Import only `weibosdkcore.jar`: Due to authentication, sharing, RESTful APIs.
    - 1.1. Import libs/\*/\*.so  **Important!!**
2. Import WeiboSDK project(Library): Due to authentication, sharing, Login/Logout buttons, OpenAPI.

Details: In **Choose integration** chapter, [Weibo-Android-SDK-v3.1.1.cn.pdf][1]

### 4. Add required premissions into application

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### 5. Add required activiteis and services into application

```xml
<activity android:name="com.sina.weibo.sdk.component.WeiboSdkBrowser"
    android:configChanges="keyboardHidden|orientation"
    android:windowSoftInputMode="adjustResize"
    android:exported="false" >
</activity>

<service android:name="com.sina.weibo.sdk.net.DownloadService"
    android:exported="false">
</service>
```

------

## How to use authentication for 3rd-party

### 1. Setup APP_KEY and other configuration.

Please read inline comments.

```java
public interface Constants {
    /**
     * Replace with your application key.
     */
    public static final String APP_KEY      = "2045436852";

    /**
     * Suggest to use default redirect url: https://api.weibo.com/oauth2/default.html
     */
    public static final String REDIRECT_URL = "http://www.sina.com";

    /**
     * Suggest to use empty scope until needed.
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
}

```

### 2. Create Weibo API instance

```java
mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
```

### 3. Implement WeiboAuthListener

```java
class AuthListener implements WeiboAuthListener {

    @Override
    public void onComplete(Bundle values) {
        mAccessToken = Oauth2AccessToken.parseAccessToken(values);
        if (mAccessToken.isSessionValid()) {
            // Save to SharePreferences
            AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
            // ...
        } else {
            // Incorrect signature
            String code = values.getString("code", "");
            // ...
        }
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onWeiboException(WeiboException e) {
    }
}
```

### 4. Authentication

* 1. Web Authentication:

```java
mSsoHandler = new SsoHandler(WBAuthActivity.this, mAuthInfo);
mSsoHandler.authorizeWeb(new AuthListener());
```

* 2. SSO Authentication:

```java
mSsoHandler = new SsoHandler(WBAuthActivity.this, mAuthInfo);
mSsoHandler. authorizeClientSso(new AuthListener());
```

* 3. AllInOne/Hybrid authentication in the following code snippet:

```java
mSsoHandler = new SsoHandler(WBAuthActivity.this, mAuthInfo);
mSsoHandler. authorize(new AuthListener());
```

* NOTICE: Depend on mobile device is installed Weibo-app or not, using according SSO authentication or Webpage authentication.

Above three ways of authentication, need to add the following code snippet of `onActivityResult` in `Activity`:

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (mSsoHandler != null) {
        mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }
}
```

## Usage of RESTful framework

For V2.5.0+, We provides synchronized and asynchronized APIs:

* Asynchronization: `AsyncWeiboRunner#requestAsync(String, WeiboParameters, String, RequestListener)`
* Synchronization: `AsyncWeiboRunner#request(String, WeiboParameters, String)`

### For Example: Using asynchronized API with one attachment of image

```java
// 1. Prepare needed image
Drawable drawable = getResources().getDrawable(R.drawable.ic_com_sina_weibo_sdk_logo);
Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

// 2. Using Weibo API
WeiboParameters params = new WeiboParameters();
params.put("access_token", mAccessToken.getToken());
params.put("status",       "Via Weibo API upload");
params.put("visible",      "0");
params.put("list_id",      "");
params.put("pic",          bitmap);
params.put("lat",          "14.5");
params.put("long",         "23.0");
params.put("annotations",  "");

AsyncWeiboRunner.requestAsync(
        "https://api.weibo.com/2/statuses/upload.json",
        params,
        "POST"
        mListener);
```

Need to prepare `mAccessToken` and `mListener`.

## Edition

```
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-ldpi/ic_com_sina_weibo_sdk_logo.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-xhdpi/ic_com_sina_weibo_sdk_logo.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-ldpi/ic_com_sina_weibo_sdk_login_with_text.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-hdpi/ic_com_sina_weibo_sdk_logo.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-mdpi/ic_com_sina_weibo_sdk_logo.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-hdpi/ic_com_sina_weibo_sdk_login_with_text.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
AAPT: libpng error: Not a PNG file
AAPT out(1532235415) : No Delegate set : lost message:Crunching /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-xhdpi/ic_com_sina_weibo_sdk_button_blue_pressed.9.png
AAPT out(1532235415) : No Delegate set : lost message:Crunching /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-ldpi/ic_com_sina_weibo_sdk_login_with_account_text_normal.png
AAPT out(1532235415) : No Delegate set : lost message:Crunching single PNG file: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-ldpi/ic_com_sina_weibo_sdk_login_with_account_text_normal.png
AAPT out(1532235415) : No Delegate set : lost message:  Output file: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/res/debug/drawable-ldpi/ic_com_sina_weibo_sdk_login_with_account_text_normal.png
AAPT out(1532235415) : No Delegate set : lost message:Done
AAPT: /home/weibo/android/weibo_android_sdk/weibo-demo/build/intermediates/exploded-aar/weibo_android_sdk/weibo-sdk/unspecified/res/drawable-mdpi/ic_com_sina_weibo_sdk_login_with_text.png: libpng warning: iCCP: Not recognizing known sRGB profile that has been edited
:weibo-demo:processDebugManifest
:weibo-demo:processDebugResources
 Position 42:30-60 : No resource found that matches the given name (at 'src' with value '@drawable/ic_share_music_thumb').
```

```sh
$ file weibo-demo/src/main/res/drawable/ic_share_music_thumb.png
weibo-demo/src/main/res/drawable/ic_share_music_thumb.png: JPEG image data, JFIF standard 1.01
```

```sh
mv weibo-demo/src/main/res/drawable/ic_share_music_thumb.png weibo-demo/src/main/res/drawable/ic_share_music_thumb.jpg
```

Regenerate nine-patch by http://romannurik.github.io/AndroidAssetStudio/nine-patches.html

## More features

Details: [Weibo-Android-SDK-v3.1.1.cn.pdf][1]

[1]:https://github.com/8tory/weibo-android-sdk/releases/download/3.1.1/Weibo-Android-SDK-v3.1.1.cn.pdf
[2]:FAQ.cn.md
[3]:https://github.com/sinaweibosdk/weibo_android_sdk/blob/master/WeiboSDK_API-V2.4.0.CHM
[4]:https://github.com/8tory/weibo-android-sdk/releases/download/3.1.1/weibo-android-sdk-demo-debug.apk
[5]:weibo-android-sdk-demo
[6]:#usage-of-restful-framework
[7]:#for-example-using-asynchronized-api-with-one-attachment-of-image
[8]:http://sinaweibosdk.github.io/weibo_android_sdk/doc/

## Contact

* QQ: 248982250
* QQ: 284084420
* email: sdk4wb@sina.cn

## License

```
Copyright 2015 8tory, Inc.
Copyright (C) 2010-2013 The SINA WEIBO Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
