# Waker

## Purpose

To wake the Wear OS watch face at a regular interval.

## Setup

Build and install the app. Once installed, it will by default be in the disabled state.

The app requires the `SYSTEM_ALERT_WINDOW` permission, in order to be able to wake the screen from the background.

Grant this using:

```
adb shell pm grant com.garan.waker android.permission.SYSTEM_ALERT_WINDOW
```

## Use

Once setup, enable the waker using:

```
adb shell am broadcast -a com.garan.waker.SET_WAKEUP_ON com.garan.waker
```

To disable again:

```
adb shell am broadcast -a com.garan.waker.SET_WAKEUP_OFF com.garan.waker
```

## Changing the interval

Specify an interval in seconds:

```
shell am broadcast -a com.garan.waker.SET_WAKEUP_INTERVAL --ei interval_seconds <seconds> com.garan.waker
```