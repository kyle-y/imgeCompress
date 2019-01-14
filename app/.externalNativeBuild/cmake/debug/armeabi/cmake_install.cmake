# Install script for directory: /Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/opt/libjpeg-turbo")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Install shared libraries without execute permission?
if(NOT DEFINED CMAKE_INSTALL_SO_NO_EXE)
  set(CMAKE_INSTALL_SO_NO_EXE "0")
endif()

# Is this installation the result of a crosscompile?
if(NOT DEFINED CMAKE_CROSSCOMPILING)
  set(CMAKE_CROSSCOMPILING "TRUE")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib32" TYPE SHARED_LIBRARY FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/build/intermediates/cmake/debug/obj/armeabi/libturbojpeg.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib32/libturbojpeg.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib32/libturbojpeg.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Library/Android/android-ndk-r16b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib32/libturbojpeg.so")
    endif()
  endif()
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/bin" TYPE EXECUTABLE FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/tjbench")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/tjbench" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/tjbench")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Library/Android/android-ndk-r16b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/tjbench")
    endif()
  endif()
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib32" TYPE STATIC_LIBRARY FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/libturbojpeg.a")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include" TYPE FILE FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/turbojpeg.h")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib32" TYPE STATIC_LIBRARY FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/libjpeg.a")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/bin" TYPE EXECUTABLE FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/rdjpgcom")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/rdjpgcom" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/rdjpgcom")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Library/Android/android-ndk-r16b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/rdjpgcom")
    endif()
  endif()
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/bin" TYPE EXECUTABLE FILES "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/wrjpgcom")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/wrjpgcom" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/wrjpgcom")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Library/Android/android-ndk-r16b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/wrjpgcom")
    endif()
  endif()
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/doc" TYPE FILE FILES
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/README.ijg"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/README.md"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/example.txt"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/tjexample.c"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/libjpeg.txt"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/structure.txt"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/usage.txt"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/wizard.txt"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/LICENSE.md"
    )
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/man/man1" TYPE FILE FILES
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/cjpeg.1"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/djpeg.1"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/jpegtran.1"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/rdjpgcom.1"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/wrjpgcom.1"
    )
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib32/pkgconfig" TYPE FILE FILES
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/pkgscripts/libjpeg.pc"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/pkgscripts/libturbojpeg.pc"
    )
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xUnspecifiedx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include" TYPE FILE FILES
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/jconfig.h"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/jerror.h"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/jmorecfg.h"
    "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/src/main/cpp/jpeglib.h"
    )
endif()

if(NOT CMAKE_INSTALL_LOCAL_ONLY)
  # Include the install script for each subdirectory.
  include("/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/simd/cmake_install.cmake")
  include("/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/sharedlib/cmake_install.cmake")
  include("/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/md5/cmake_install.cmake")

endif()

if(CMAKE_INSTALL_COMPONENT)
  set(CMAKE_INSTALL_MANIFEST "install_manifest_${CMAKE_INSTALL_COMPONENT}.txt")
else()
  set(CMAKE_INSTALL_MANIFEST "install_manifest.txt")
endif()

string(REPLACE ";" "\n" CMAKE_INSTALL_MANIFEST_CONTENT
       "${CMAKE_INSTALL_MANIFEST_FILES}")
file(WRITE "/Users/yuexiaobo/android/AndroidStudioProjects/self/imgeCompress/app/.externalNativeBuild/cmake/debug/armeabi/${CMAKE_INSTALL_MANIFEST}"
     "${CMAKE_INSTALL_MANIFEST_CONTENT}")
