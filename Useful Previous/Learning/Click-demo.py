#coding=utf-8
import os
from time import sleep
from selenium import webdriver





desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '4.4'
desired_caps['deviceName'] = '05837ca5'
desired_caps['appPackage'] = 'com.pada.padasf'
desired_caps['appActivity'] = '.LoginActivity'

dr = webdriver.Remote('http://localhost:4723/wd/hub',desired_caps)
sleep(1)

dr.find_element_by_id('com.pada.padasf:id/login_btn').click()  #点击登陆



