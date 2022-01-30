import pandas as pd
from sqlalchemy import create_engine
import pymysql

df = pd.read_csv('dataset.csv')

#password - ashishkumarakp
#table_name - workconnect
#db_name - retrieved_info
#username - root
#host - localhost

my_conn=create_engine("mysql+mysqldb://root:ashishkumarakp@localhost/workconnect")
df.to_sql(con=my_conn,name='retrieved_info',if_exists='replace')
print("Uploaded to database")
