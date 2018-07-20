# Pocket Api for Android
This is a sample Android project for for working with the [Pocket](https://getpocket.com/about) api.

I'm not sure why, but the Pocket team don't update thier [developer api documentation](https://getpocket.com/developer/docs/overview) much, and there is no info about it out there, so after I managed to get some things working I decided to share it if someone will ever need it.

In this app you can find:

1. Basic authentication process, using the Pocket app (if exists) or web view (as fallback).
2. Retrieving articles list for a logged in user

For using the Pocket api you need the following:
- Creating a [new pocket app](https://getpocket.com/developer/apps/new)- make sure you're requesting the right permissions that you need.
- A customer key- you will get one for each app you create. A Pocket consumer key looks like this: `1234-abcd1234abcd1234abcd1234`

Please add both customer key in the right place in the app Manifest.
