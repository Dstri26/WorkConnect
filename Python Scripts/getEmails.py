from operator import index
import requests
import json
import pandas as pd
import numpy as np
from test import *


#slack bot don't have a email param
index_of_bot = 0
for i in range(len(user_slack)):
  if user_slack[i] == 'U030NQSKSCB':
    index_of_bot=i

temp = user_slack
temp.remove('U030NQSKSCB')
#print(user_slack)



#Credentials for slack
bot_token = 'xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q'
url = "https://slack.com/api/users.info"
headers = {'Authorization': 'Bearer ' +bot_token, 'Content-type': 'application/x-www-form-urlencoded'}
emails = []
ids= []

for i in temp:
    user = {'user':i}
    r = requests.get(url,params=user,headers=headers)
    mail_id_json = r.json()
    emails.append(mail_id_json['user']['profile']['email'])
    ids.append(mail_id_json['user']['id'])

emails_slack = []
for i in range(len(ids)):
  if ids[i] =='U02U5MXFJ4Q':
    emails_slack.append(emails[i])
  elif ids[i] == "U02TG0HF9JN":
    emails_slack.append(emails[i])
  elif ids[i] == "U02TG0HGTU2":
    emails_slack.append(emails[i])
  elif ids[i] == "U030NQSKSCB":
    emails_slack.append("Null")
  else:
    emails_slack.append(emails[i])
#df = pd.read_csv('dataset.csv')
#df['Emails'] = emails_slack
emails_slack.insert(index_of_bot,"Null")


data = {"Texts-From-Channel":texts_slack,"Time-Stamp":time_stamp_slack,"User-Name":names_slack,"Emails":emails_slack}
data_frame = pd.DataFrame(data)



#df.to_csv("Final_dataset.csv",index=False)
