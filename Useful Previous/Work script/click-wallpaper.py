#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

'''Use for change the wallpaper of launcher'''

def click():

    #�����λ��
    os.system('adb shell input tap 500 1000')
    sleep(1)

def clickNo_1():
    os.system('adb shell input tap 700 1000')
    sleep(1)

def clickNo_2():
    os.system('adb shell input tap 9000 1000')
    sleep(1)

# def clickNo_3():
#     os.system('adb shell input keyevent KEYCODE_HOME')
#     sleep(0)
#     # #����
#     # os.system('adb shell input swipe 200 300 200 520')
#     # sleep(0)

if __name__ == '__main__':
    total_times = 100
    times = 1

    while (times <= total_times):
        click()
        clickNo_1()
        clickNo_2()
        # clickNo_3()
        print times
        times += 1