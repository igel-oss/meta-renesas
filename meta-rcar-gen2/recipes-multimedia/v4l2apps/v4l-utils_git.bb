SECTION = "libs"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file:///${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "virtual/gettext"

SRC_URI = "git://git.linuxtv.org/v4l-utils.git;protocol=git \
	file://0001-libv4l2-Refactor-v4l2_mmap.patch \
	file://0002-libv4l2-Support-mmap-to-libv4l-plugin.patch \
"
SRCREV = "aeacb184e815096b55d54948b1c0e089eea57525"

S = "${WORKDIR}/git"
do_install[dirs] = "${WORKDIR}/git"
do_cleansstate[dirs] = "${WORKDIR}/git"
do_configure[dirs] = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += " \
		--target=arm-poky-linux-gnueabi --host=arm-poky-linux-gnueabi --build=x86_64-linux \
		--with-libtool-sysroot=$PKG_CONFIG_SYSROOT_DIR \
		--without-jpeg --without-libudev --disable-v4l-utils \
"

do_configure () {
#	cd ${S}
	./bootstrap.sh
	autotools_do_configure
}

do_install_append () {
	install -d ${D}/usr/local/include
	install -m 0644 ${S}/lib/include/libv4l-plugin.h ${D}/usr/local/include
}

FILES_${PN}-dbg += "\
	${libdir}/*/.debug/*.so \
	${libdir}/*/*/.debug/*.so \
	${libdir}/*/.debug/ov511-decomp \
	${libdir}/*/.debug/ov518-decomp \
"

FILES_${PN}-dev += "\
	${libdir}/*.so \
	${libdir}/*.la \
	${libdir}/*/*.la \
	${libdir}/*/*/*.la \
	${libdir}/pkgconfig \
"

FILES_${PN}-staticdev += "\
	${libdir}/*.a \
"

FILES_${PN}-headers = "/usr/local/include"

FILES_${PN} += "\
	${libdir}/*.so \
	${libdir}/*.so.* \
	${libdir}/libv4l/*.so \
	${libdir}/libv4l/plugins/*.so \
	${libdir}/libv4l/ov511-decomp \
	${libdir}/libv4l/ov518-decomp \
"

PACKAGES += "\
	${PN}-headers \
"

