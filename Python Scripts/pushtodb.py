import pandas as pd
from sqlalchemy import create_engine
import pymysql
from getmails import data_frame

#df = pd.read_csv('Final_dataset.csv')

my_conn=create_engine("mysql+mysqldb://root:ashishkumarakp@localhost/workconnect")
data_frame.to_sql(con=my_conn,name='retrieved_info',if_exists='replace')
print("Uploaded to database")
