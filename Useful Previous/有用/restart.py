#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

def gamecencer():
	#������Ϸ����,ͨ��adb logcat -s ActivityManager��ȡ����app������
	os.system('adb shell am start -S com.jianui.filemanager/.FileExplorerTabActivity')
	sleep(1)

    #���ĳ��λ��
	os.system('adb shell input tap 900 1300')
	sleep(1)

 #    �����ؼ��˳�Ӧ��
	# os.system('adb shell input keyevent 4')
	# sleep(2)

if __name__ == '__main__':
	total_times = 3
	times = 1

	while (times <= total_times):
		gamecencer()
		print times
		times += 1