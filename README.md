Overview
========

This repository contains the recipes necessary to build the
[v4l-gst libv4l plugin](https://github.com/igel-oss/v4l-gst)
and the integration module to access it from Chromium.

The plugin can be used to connect the Chromium hardware video decode
acceleration framework to GStreamer through the V4L2 API.

This recipe supports optional integration with Chromium 40, which requires the
[meta-browser](https://github.com/OSSystems/meta-browser) layer to be included in
your yocto configuration.

Building
========

Add the following packages to your IMAGE_INSTALL_append variable in your local.conf
* v4l-gst
* v4l-gst-bufferpool-rel
* gstreamer1.0-plugins-base-app
* libv4l

Configuration
=============

Create the settings file ```/etc/xdg/libv4l-gst.conf```. The following settings are for the
Renesas Porter board, but they may be updated to use more generic settings.

```
[libv4l-gst]
pipeline=h264parse ! omxh264dec ! queue max-size-bytes=0 max-size-time=0 max-size-buffers=0 ! vspfilter
bufferpool-library=/usr/lib/libv4l/plugins/v4l-gst-bufferpool/libv4l-gst-bufferpool-rel.so
min-buffers=2
```

Running
=======

Create a dummy V4L2 device file under /dev
```
# touch /dev/video-gst
# chmod 666 /dev/video-gst
```
Accessing the /dev/video-gst file will allow an application to use the v4l-gst plugin
using the same API as a regular V4L2 device file.

Running with Chromium
---------------------

Hardware video decoding is blacklisted by default on Chromium. Add the ```--ignore-gpu-blacklist``` flag
to the Chromium command line to enable video decoding via the V4L2 interface.
