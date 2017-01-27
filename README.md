# Pocket Api for Android
This is a sample Android project for for working with the [Pocket](https://getpocket.com/about) api.

I'm not sure why, but the Pocket team don't update thier [developer api documentation](https://getpocket.com/developer/docs/overview) much, and there is no info about it out there, so after I managed to get some things working I decided to share it if someone will ever need it.

In this app you can find:
1. Basic authentication process, using the Pocket app (if exists) or web view (as fallback).
2. Retrieving articles list for a logged in user

For using the Pocket api you need the following:
- Creating a [new pocket app](https://getpocket.com/developer/apps/new)- make sure you're requesting the right permissions that you need.
- A customer key- you will get one for each app you create. A Pocket consumer key looks like this: `1234-abcd1234abcd1234abcd1234`
- A redirect url- this is the url where the user will be redirected after he will successfully log in. It contains scheme and path. By default, the scheme is "pocketapp" plus your application's ID (which you can find at the beginning of the consumer key before the hyphen). So if your consumer key is `1234-abcdef...`, your app ID is `1234`, and your URL scheme will be `pocketapp1234`. An in our case the redirected url will be `pocketapp1234:authorizationFinished`

Please add both customer key and redirect url in the right place in the app Manifest.
