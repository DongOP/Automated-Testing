#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

def filemanager():
	#�����˺�����,ͨ��adb logcat -s ActivityManager��ȡ����app������
	os.system('adb shell am start com.pada.padasf/.PadaAccountCenterActivity')
	sleep(1)

    #�������,̨��P80-�˺�
	os.system('adb shell input tap 260 636')
	sleep(1)

    #�汾����ִ�д���
	total_times = 50
	times = 1

	while (times <= total_times):
		upgrade()
		print times
		times += 1


def upgrade():
    #����汾����
	os.system('adb shell input tap 600 160')
	sleep(3)

if __name__ == '__main__':
	filemanager()