#coding=utf-8
import os

import time

import unittest

from selenium import webdriver

from lib2to3.pgen2.driver import Driver

from lib2to3.tests.support import driver

'''测试'游戏中心'登陆功能

运行脚本前先打开Appium

'''

#设置路径信息
PATH=lambda p:os.path.abspath(

os.path.join(os.path.dirname(__file__),p)

)

global driver

class LoginAndroidTests(unittest.TestCase):

    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '5.0'
        desired_caps['deviceName'] = 'Baytrail9DD3CC40'
        desired_caps['appPackage'] = 'com.pada.padasf'
        desired_caps['appActivity'] = '.LoginActivity'

        self.driver=webdriver.Remote('http://localhost:4723/wd/hub',desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_login(self):
        time.sleep(1)

        button = self.driver.find_element_by_id("com.pada.padasf:id/login_btn")  #点击登陆按钮
        button.click()
        time.sleep(1)

        name = self.driver.find_element_by_id('com.pada.padasf:id/et_username')
        name.click()
        name.send_keys("luopingting") #输入账号

        psd = self.driver.find_element_by_id('com.pada.padasf:id/et_pwd')
        psd.click()
        psd.send_keys('12345678') #输入密码

        blogin=self.driver.find_element_by_id('com.pada.padasf:id/right_handle')  #单击登录按钮
        blogin.click()
        time.sleep(1)

if __name__ == '__main__':

    suite = unittest.TestLoader().loadTestsFromTestCase(LoginAndroidTests)

    unittest.TextTestRunner(verbosity=2).run(suite)