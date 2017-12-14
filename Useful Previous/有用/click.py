#!/usr/bin/python
#coding=utf-8

import os
from time import sleep

def click():

    #点击的位置
    os.system('adb shell input tap 200 800')
    sleep(0)

    # #滑动
    # os.system('adb shell input swipe 200 300 200 520')
    # sleep(0)

if __name__ == '__main__':
    total_times = 100
    times = 1

    while (times <= total_times):
        click()
        print times
        times += 1