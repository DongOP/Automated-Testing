#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

'''Use for change the theme of launcher'''

def click():

    #点击的位置
    os.system('adb shell input tap 500 1000')
    sleep(0)

def clickNo_1():
    os.system('adb shell input tap 600 1000')
    sleep(0)

def clickNo_2():
    os.system('adb shell input tap 800 1000')
    sleep(0)


def clickNo_3():
    os.system('adb shell input tap 1000 1000')
    sleep(0)
    # #滑动
    # os.system('adb shell input swipe 200 300 200 520')
    # sleep(0)
    # 输入文字
    # adb shell input text test123456
    # 发送按键事件
    # adb shell input keyevent KEYCODE_HOME
    # 滑动事件
    # adb shell input swipe 900 500 100 500
    # 截图
    # adb shell screencap -p /sdcard/screen.png
    # 

if __name__ == '__main__':
    total_times = 100
    times = 1

    while (times <= total_times):
        click()
        clickNo_1()
        clickNo_2()
        clickNo_3()
        print times
        times += 1