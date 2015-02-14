#ifndef __COMMON_H__
#define __COMMON_H__

#include <cutils/log.h>

#define DEBUG 1

#if DEBUG
#define  D(...)  ALOGD(__VA_ARGS__)
#else
#define  D(...)  ((void)0)
#endif

#define  E(...)  ALOGE(__VA_ARGS__)


#endif __COMMON_H__
