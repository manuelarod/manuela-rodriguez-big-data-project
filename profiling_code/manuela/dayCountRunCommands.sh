javac -classpath `yarn classpath` -d . DayCountMapper.java
javac -classpath `yarn classpath` -d . DayCountReducer.java
javac -classpath `yarn classpath`:. -d . DayCount.java
jar -cvf dayCount.jar *.class
hadoop jar dayCount.jar DayCount citibike_data/output/part-r-00000 /user/mr5340/citibike_data/day_count_output
