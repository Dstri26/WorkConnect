#required imports
import requests
import json
import pandas as pd
import numpy as np
from textwrap import indent
import pandas as pd
from sqlalchemy import create_engine
import pymysql

#Credentials for slack
token = 'xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q'
headers = {'Authorization': 'Bearer ' + token, 'Content-type': 'application/x-www-form-urlencoded'}
channel = 'C0313AG3ZPB'
payload = {'channel':channel}
conv_history_url = "https://slack.com/api/conversations.history"

#collecting json from API
r = requests.get(url=conv_history_url,params=payload,headers=headers)
conv_history_json_object = r.json()
with open('conv_history.json', 'w') as f:
    json.dump(conv_history_json_object, f)

#Creating a Dataframe of User-Texts,Time-Stamp and Username
conv_history_df = pd.read_json('conv_history.json')
texts_slack = []
time_stamp_slack = []
user_slack = []
names_slack = []
#print(len(df['messages'].keys()))
#print(df['messages'][13]['text'])
for i in range(len(conv_history_df['messages'].keys())):
  texts_slack.append(conv_history_df['messages'][i]['text'])
  time_stamp_slack.append(conv_history_df['messages'][i]['ts'])
  user_slack.append(conv_history_df['messages'][i]['user'])

for i in user_slack:
  if i =='U02U5MXFJ4Q':
    names_slack.append("AKP")
  elif i == "U02TG0HF9JN":
    names_slack.append("Anisha")
  elif i == "U02TG0HGTU2":
    names_slack.append("Prajojita")
  elif i == "U030NQSKSCB":
    names_slack.append("BotApp")
  else:
    names_slack.append("Trideep")


data = {"Texts-From-Channel":texts_slack,"Time-Stamp":time_stamp_slack,"User-Name":names_slack}
data_frame = pd.DataFrame(data)
#print(data_frame)
data_frame.to_csv("dataset.csv",index=False)
print("Data created successfully")
