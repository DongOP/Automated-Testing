#coding=utf-8
import os

import time

import unittest

from selenium import webdriver

from lib2to3.pgen2.driver import Driver

from lib2to3.tests.support import driver



'''unittest for calc'''

PATH=lambda p:os.path.abspath(

os.path.join(os.path.dirname(__file__),p)

)

global driver

class CalculatorAndroidTests(unittest.TestCase):
    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '4.4'
        desired_caps['deviceName'] = 'Baytrail01C6B441'
        desired_caps['appPackage'] = 'com.android.calculator2'
        desired_caps['appActivity'] = '.Calculator'

        self.driver=webdriver.Remote('http://localhost:4723/wd/hub',desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_calclator(self):
        time.sleep(1)

        nine = self.driver.find_element_by_id('com.android.calculator2:id/digit9')
        nine.click()
        time.sleep(1)

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(CalculatorAndroidTests)
    unittest.TextTestRunner(verbosity=2).run(suite)

