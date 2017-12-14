#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

def filemanager():
	#启动账号中心,通过adb logcat -s ActivityManager获取启动app的类名
	os.system('adb shell am start com.pada.padasf/.PadaAccountCenterActivity')
	sleep(1)

    #点击设置,台电P80-八核
	os.system('adb shell input tap 260 636')
	sleep(1)

    #版本升级执行次数
	total_times = 50
	times = 1

	while (times <= total_times):
		upgrade()
		print times
		times += 1


def upgrade():
    #点击版本升级
	os.system('adb shell input tap 600 160')
	sleep(3)

if __name__ == '__main__':
	filemanager()