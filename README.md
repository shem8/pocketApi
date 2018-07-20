# Pocket Api for Android
Android library for working with the [Pocket](https://getpocket.com/about) api.


## Download

Gradle

Project level build.gradle
```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

App level build.gradle

```xml
dependencies {
    implementation 'com.github.shem8:pocketapi:v1.1.0'
    
    //I'm using retrfit to fetch data
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.google.code.gson:gson:2.6.1'
    implementation 'com.squareup.retrofit2:converter-scalars:2.1.0'
}
```

## Usage

For using the Pocket api you need the following:
- Creating a [new pocket app](https://getpocket.com/developer/apps/new)- make sure you're requesting the right permissions that you need.
- A customer key- you will get one for each app you create. A Pocket consumer key looks like this: `1234-abcd1234abcd1234abcd1234`

Please add customer key in the right place in the app Manifest under:
```
<meta-data
    android:name="shem.pocketapi.CustomerKey"
    android:value="XXXXX-XXXXXXXXXXXXXXXXXXXXXXXX"/>
```

### Login:

Start the login flow somewhere in your app:
```
PocketApi.login(this);
```

Handle the result in your activity:
```
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == PocketApi.LOGIN_REQUEST_CODE) {
        //Use the status from PocketApi.isLoggedIn and handle accordingly
    }
    ...
}

```

Full API:

```java
public class PocketApi {
    //Begin the login flow
    public static void login(Activity c);

    //Logout and clear the user's session
    public static void logout(Activity c);

    //Check if the user has a valid session
    public static boolean isLoggedIn(Context c);

    //Get the current user name
    public static String getUsername(Context c);

    //Get the user's articles
    public static void getArticles(Context c, Callback<PocketService.ArticlesMap> callback);
}
```

You can also fork the project and see the sample app:

# Sample App
I'm not sure why, but the Pocket team don't update thier [developer api documentation](https://getpocket.com/developer/docs/overview) much, and there is no info about it out there, so after I managed to get some things working I decided to share it if someone will ever need it.

In this app you can find:

1. Basic authentication process, using the Pocket app (if exists) or web view (as fallback).
2. Retrieving articles list for a logged in user


# Contact Me

Pull requests are more than welcome.
You can also contact me by mail: smagnezi8@gmail.com



# License

    Copyright 2017 Shem Magnezi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.