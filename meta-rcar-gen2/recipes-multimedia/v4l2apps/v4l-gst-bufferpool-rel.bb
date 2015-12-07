SECTION = "libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file:///${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

SECTION = "libs"

DEPENDS = "libdrm glibmm gstreamer1.0 gstreamer1.0-plugins-base v4l-utils v4l-gst"

SRC_URI = "git://github.com/igel-oss/v4l-gst-bufferpool-rcar.git;protocol=https"
SRCREV = "3c699b83dd2ef94103056c10fd102d4bd1edc6b2"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

TARGET_CFLAGS += "\
	-I${STAGING_INCDIR}/libdrm \
	-I${STAGING_INCDIR}/libkms \
"

FILES_${PN}-dbg += "\
	${libdir}/libv4l/plugins/v4l-gst-bufferpool/.debug \
	${libdir}/libv4l/plugins/v4l-gst-bufferpool/.debug/*.so \
"

FILES_${PN}-dev += "\
	${libdir}/libv4l/plugins/v4l-gst-bufferpool/*.la \
"

FILES_${PN} += "\
	${libdir}/libv4l/plugins/v4l-gst-bufferpool/*.so \
"

