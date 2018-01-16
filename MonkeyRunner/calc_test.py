#coding:utf-8
'''
通过获取 HierarchyViewer 对象来获取控件。

同样也可以使用 EasyMonkeyDevice
'''
import sys

from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice,MonkeyImage

logFile = open("C:\\Users\\Administrator\\Desktop\\Automated-Testing\\MonkeyRunner\\calc_log.txt","a+")

#连接设备
device = MonkeyRunner.waitForConnection()
if not device:
    print("Please connect a device to start!")
    logFile.write("Please connect a device to start!\n")
else:
    print("Device Connected successfully!")
    logFile.write("Device Connected successfully!\n")
    
package = 'com.android.calculator2' 
activity = 'com.android.calculator2.Calculator'
runComponent = package + '/' + activity

#启动计算器
print("Start calculator")
logFile.write("Start calculator")
device.startActivity(component=runComponent)

#等待时间
print("Wait 5s")
logFile.write("Wait 5s")
MonkeyRunner.sleep(5)

#获取控件对象
print("Get widget object")
logFile.write("Get widget object")
hierarchyviewer = device.getHierarchyViewer()
#得到8,9,*,=的viewnode对象
#举例：viewNode对象的值：com.android.calculator2.CalculatorDisplay@b3ffd170 
viewDigit8 = hierarchyviewer.findViewById("id/digit8")
viewDigit9 = hierarchyviewer.findViewById("id/digit9")
viewMul = hierarchyviewer.findViewById("id/mul")
viewEqual = hierarchyviewer.findViewById("id/equal")

#获取控件的中心坐标
#举例：pointView的中心点坐标的值：Point {240, 116}
print("Get location of view")
logFile.write("Get location of view")
pointViewDigit8 = hierarchyviewer.getAbsoluteCenterOfView(viewDigit8)
pointViewDigit9 = hierarchyviewer.getAbsoluteCenterOfView(viewDigit9)
pointViewMul = hierarchyviewer.getAbsoluteCenterOfView(viewMul)
pointViewEqual = hierarchyviewer.getAbsoluteCenterOfView(viewEqual)

#计算8*9=72的结果
print("touch 8*9=")
logFile.write("touch 8*9=")
MonkeyRunner.sleep(5)
device.touch(pointViewDigit8,'DOWN_AND_UP')
device.touch(pointViewMul,'DOWN_AND_UP')
device.touch(pointViewDigit9,'DOWN_AND_UP')
device.touch(pointViewEqual,'DOWN_AND_UP')

logFile.close()