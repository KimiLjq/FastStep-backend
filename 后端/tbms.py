# -*- coding: utf-8 -*-
# 淘宝秒杀
'''
运行需求：
1. 安装python库：selenium， pymysql
2. 安装谷歌浏览器驱动
'''
from selenium import webdriver
import selenium
import pymysql as pq
import time

ua = u'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36'
options = webdriver.ChromeOptions()
options.add_argument('--log-level=3')
options.add_argument('user-agent=' + ua)
driver = webdriver.Chrome(chrome_options=options)
# driver.maximize_window()
url = r'http://miaosha.taobao.com/'
driver.get(url)


def crawl():
    for i in range(0, 5):
        try:
            button = driver.find_element_by_xpath('//*[@id="J_TimeNav"]/li[' +
                                                  str(i + 1) + ']')
            button.click()
        except selenium.common.exceptions.NoSuchElementException:
            if i == 0:
                print('今日没有秒杀')
                break
            else:
                print('秒杀爬取完成')
                break
        else:
            stratTime = driver.find_element_by_xpath(
                '//*[@id="J_TimeNav"]/li/a/span[1]').text
            endTime = driver.find_element_by_xpath(
                '//*[@id="J_TimeNav"]/li/a/span[2]').text
            sqlcon(stratTime, endTime)


def crawlDetail(stratTime, endTime, cursor):
    data = []
    for num in range(1, 10):
        try:
            driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) + ']/h4/a')
        except selenium.common.exceptions.NoSuchElementException:
            print()
            break
        else:
            itemName = driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) +
                ']/h4/a').text
            itemLink = driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) +
                ']/h4/a').get_attribute('href')
            itemPicLink = driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) +
                ']/div[1]/a/img').get_attribute('src')
            itemPrice = driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) +
                ']/div[2]/table/tbody/tr[2]/td[1]/span').text
            itemPrePrice = driver.find_element_by_xpath(
                '//*[@id="J_HomeList"]/div[1]/div/div[' + str(num) +
                ']/div[2]/table/tbody/tr[2]/td[2]').text
        time.sleep(0.3)
        data = [itemName, itemPrePrice, itemPrice, itemLink, itemPicLink, stratTime, endTime]
        print(data)
        cursor.execute('insert into MIAOSHA (itemName, oriPrice, prePrice, itemLink, picLink, satrtTime, endTime) \
                        values(%s %s %s %s %s %s %s )'[data[0], data[1], data[2], data[3], data[4], data[5], data[6]])


def sqlcon(stratTime, endTime):
    # here we need to modify the host, user, password and database
    conn = pq.connect(host="127.0.0.1",
                      port=3306,
                      user="root",
                      password="123456",
                      database="fastStep",
                      use_unicode=True)
    cursor = conn.cursor()
    if (cursor.execute('SHOW TABLES LIKE "MIAOSHA"')):
        tabexit = "DROP TABLE IF EXISTS MIAOSHA"    # 如果存在就删除数据表
        cursor.execute(tabexit)
    Course = "create table MIAOSHA (itemName varchar(100), oriPrice float(10) , prePrice float(10),\
                itemLink varchar(100) , picLink varchar(100), startTime varchar(10), endTime varchar(10))"
    cursor.execute(Course)
    # 调用爬虫爬取
    crawlDetail(stratTime, endTime, cursor)
    conn.commit()
    cursor.close()
    conn.close()


if __name__ == "__main__":
    crawl()
    driver.quit()
    driver.__exit__
