#-*- coding: UTF-8 -*-

#导入常用的包

import os

import time

import unittest

from selenium import webdriver

from lib2to3.pgen2.driverimport Driver

from lib2to3.tests.supportimport driver

 

#设置路径信息

PATH=lambda p:os.path.abspath(

os.path.join(os.path.dirname(__file__),p)                            

)

global driver

 

class LoginAndroidTests(unittest.TestCase):

def setUp(self):

   #初始化测试平台

       desired_caps={}

       desired_caps['device'] = 'android'

       desired_caps['platformName']='Android' #测试平台

       desired_caps['browserName']=''

       desired_caps['version']='4.4.2'#系统版本

       desired_caps['deviceName']='test'#模拟器名称

       desired_caps['app-package']='com.subject.zhongchou'#要测试的app包名

       desired_caps['app-activity']='.ZhongChou'#当前活动应用

       self.driver=webdriver.Remote('http://localhost:4723/wd/hub',desired_caps)

       

   def tearDown(self):

       self.driver.quit()

   

   def test_login(self):

       time.sleep(30)

       #点击“注册登录”按钮

       button=self.driver.find_element_by_id("com.subject.zhongchou:id/register_button")

       button.click()

       time.sleep(10)

       #登录

       name = self.driver.find_element_by_id('com.subject.zhongchou:id/loginnumber_phone')

       name.click()

       name.send_keys('qwertyzdd') #输入用户名

       psd = self.driver.find_element_by_id('com.subject.zhongchou:id/loginnumber_password')

       psd.click()

       psd.send_keys('qwertyzdd') #输入密码

       blogin=self.driver.find_element_by_id('com.subject.zhongchou:id/go_numberlogin')  #单击登录按钮

       blogin.click()

       time.sleep(10)

       #此处要检测是否登录成功

if __name__ == '__main__':

   suite =unittest.TestLoader().loadTestsFromTestCase(LoginAndroidTests)

   unittest.TextTestRunner(verbosity=2).run(suite)