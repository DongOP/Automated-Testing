# -*- coding:utf-8 -*-

import sys
import io,gzip
import json
import urllib
import urllib2

pycoding=sys.getfilesystemencoding()

def gzip_decompress(data):
    """Decompress a gzip compressed string in one shot.
    Return the decompressed string.
    """
    with gzip.GzipFile(fileobj=io.BytesIO(data)) as f:
        return f.read()

def lookup_dict(word):
    url='http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=http://www.baidu.com/s'
    user_agent='Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    values={'type':'Auto',
              'i':word,
              'doctype':'json',
              'xmlVersion':'1.4',
              'keyfrom':'fanyi.web',
              'ue':'UTF-8',
              'typoResult':'true',
              'flag':'false'
              }
    headers={ 'User-Agent':'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)',
                'Accept':'application/json, text/javascript, */*',
                'Accept-Charset':'GBK,utf-8',
                'Accept-Encoding':'gzip,deflate',
                'Accept-Language':'zh-CN,zh',
                'Origin':'http://fanyi.youdao.com',
                'Referer':'http://fanyi.youdao.com/',
                'X-Requested-With':'XMLHttpRequest'
                }
    data=urllib.urlencode(values)
    req=urllib2.Request(url, data.encode("gb2312"), headers)
    resp=urllib2.urlopen(req)
    page=resp.read()
    resp.close()
    last=gzip_decompress(page).decode("utf-8")
    x=json.JSONDecoder().decode(last)
    result=x["smartResult"]
    print(result['type'])
    print('\n'.join(result['entries']))

while True:
    find=raw_input('输入你要查找的单词:'.decode('utf-8').encode(pycoding))
    if find=='*':
        break
    elif find.strip():
        lookup_dict(find)
