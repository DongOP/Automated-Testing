#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

def gamecencer():
	#启动游戏中心,通过adb logcat -s ActivityManager获取启动app的类名
	os.system('adb shell am start -S com.jianui.filemanager/.FileExplorerTabActivity')
	sleep(1)

    #点击某个位置
	os.system('adb shell input tap 900 1300')
	sleep(1)

 #    按返回键退出应用
	# os.system('adb shell input keyevent 4')
	# sleep(2)

if __name__ == '__main__':
	total_times = 3
	times = 1

	while (times <= total_times):
		gamecencer()
		print times
		times += 1