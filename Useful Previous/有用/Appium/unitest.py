#coding=utf-8
import os

import time

import unittest

from selenium import webdriver

from lib2to3.pgen2.driver import Driver

from lib2to3.tests.support import driver

# Returns abs path relative to this file and not cwd
PATH = lambda p: os.path.abspath(
    os.path.join(os.path.dirname(__file__), p)
)

class CalculatorAndroidTests(unittest.TestCase):
    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '4.4'
        desired_caps['deviceName'] = 'Baytrail01C6B441'
        '''desired_caps['app'] = PATH(
            '../../../sample-code/apps/ContactManager/ContactManager.apk'
        )'''
        desired_caps['appPackage'] = 'com.android.calculator2'
        desired_caps['appActivity'] = '.Calculator'

        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

    def tearDown(self):
        # self.driver.close_app()
        self.driver.quit()

    def test_add_contacts(self):
        n1 = self.driver.find_element_by_id('com.android.calculator2:id/digit9')
        n1.click()
        time.sleep(1)

        n2 = self.driver.find_element_by_id("com.android.calculator2:id/plus")
        n2.click()
        time.sleep(1)

        n3 = self.driver.find_element_by_id("com.android.calculator2:id/digit6")
        n3.click()
        time.sleep(1)
        
        n4 = self.driver.find_element_by_id("com.android.calculator2:id/equal")
        n4.click()

        self.driver.quit()



if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(CalculatorAndroidTests)
    unittest.TextTestRunner(verbosity=2).run(suite)