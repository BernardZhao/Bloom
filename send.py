from urllib.request import *


import urllib
import json
import sys
MY_API_KEY="AIzaSyBYlnssxSZiKxiCTPLMMy0Q6PeA3ESHPzg"
messageTitle = sys.argv[1]
messageBody = sys.argv[2]
data={
    "to" : "/topics/my_little_topic",
    "notification" : {
        "body" : messageBody,
        "title" : messageTitle,
        "icon" : "ic_cloud_white_48dp"
    }
}
dataAsJSON = json.dumps(data)
request = Request(
    "https://gcm-http.googleapis.com/gcm/send",
    dataAsJSON.encode('utf8'),
    { "Authorization" : "key="+MY_API_KEY,
      "Content-type" : "application/json"
    }
)
print (urlopen(request).read())