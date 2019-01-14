#!/usr/bin/env bash
# Set these variables to suit your needs
NDK_PATH=/Library/Android/android-ndk-r16b
BUILD_PLATFORM="darwin-x86_64"
TOOLCHAIN_VERSION="4.9"
ANDROID_VERSION="16"

# It should not be necessary to modify the rest
HOST=i686-linux-android
SYSROOT=${NDK_PATH}/platforms/android-${ANDROID_VERSION}/arch-x86
export CFLAGS="-D__ANDROID_API__=${ANDROID_VERSION} --sysroot=${SYSROOT} \
  -isystem ${NDK_PATH}/sysroot/usr/include \
  -isystem ${NDK_PATH}/sysroot/usr/include/${HOST}"
export LDFLAGS=-pie
TOOLCHAIN=${NDK_PATH}/toolchains/x86-${TOOLCHAIN_VERSION}/prebuilt/${BUILD_PLATFORM}

cd /lib_build

cat <<EOF >toolchain.cmake
set(CMAKE_SYSTEM_NAME Linux)
set(CMAKE_SYSTEM_PROCESSOR i386)
set(CMAKE_C_COMPILER ${TOOLCHAIN}/bin/${HOST}-gcc)
set(CMAKE_FIND_ROOT_PATH ${TOOLCHAIN}/${HOST})
EOF

cmake -G"Unix Makefiles" -DCMAKE_TOOLCHAIN_FILE=toolchain.cmake \
  -DCMAKE_POSITION_INDEPENDENT_CODE=1 \
  [additional CMake flags] ../app/src/main/cpp
make