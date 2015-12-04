FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI_append = " file://0001-Enable-V4L2VDA-on-Chromium.patch \
        file://enable_rcar_v4l2device.patch \
        file://0001-Allow-GPU-GPU-video-texture-copy-when-target-is-EXTE.patch \
"

CHROMIUM_WAYLAND_GYP_DEFINES += "proprietary_codecs=1 ffmpeg_branding=Chrome"
