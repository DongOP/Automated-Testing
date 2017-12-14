#-*- coding: UTF-8 -*-

#���볣�õİ�

import os

import time

import unittest

from selenium import webdriver

from lib2to3.pgen2.driverimport Driver

from lib2to3.tests.supportimport driver

 

#����·����Ϣ

PATH=lambda p:os.path.abspath(

os.path.join(os.path.dirname(__file__),p)                            

)

global driver

 

class LoginAndroidTests(unittest.TestCase):

def setUp(self):

   #��ʼ������ƽ̨

       desired_caps={}

       desired_caps['device'] = 'android'

       desired_caps['platformName']='Android' #����ƽ̨

       desired_caps['browserName']=''

       desired_caps['version']='4.4.2'#ϵͳ�汾

       desired_caps['deviceName']='test'#ģ��������

       desired_caps['app-package']='com.subject.zhongchou'#Ҫ���Ե�app����

       desired_caps['app-activity']='.ZhongChou'#��ǰ�Ӧ��

       self.driver=webdriver.Remote('http://localhost:4723/wd/hub',desired_caps)

       

   def tearDown(self):

       self.driver.quit()

   

   def test_login(self):

       time.sleep(30)

       #�����ע���¼����ť

       button=self.driver.find_element_by_id("com.subject.zhongchou:id/register_button")

       button.click()

       time.sleep(10)

       #��¼

       name = self.driver.find_element_by_id('com.subject.zhongchou:id/loginnumber_phone')

       name.click()

       name.send_keys('qwertyzdd') #�����û���

       psd = self.driver.find_element_by_id('com.subject.zhongchou:id/loginnumber_password')

       psd.click()

       psd.send_keys('qwertyzdd') #��������

       blogin=self.driver.find_element_by_id('com.subject.zhongchou:id/go_numberlogin')  #������¼��ť

       blogin.click()

       time.sleep(10)

       #�˴�Ҫ����Ƿ��¼�ɹ�

if __name__ == '__main__':

   suite =unittest.TestLoader().loadTestsFromTestCase(LoginAndroidTests)

   unittest.TextTestRunner(verbosity=2).run(suite)