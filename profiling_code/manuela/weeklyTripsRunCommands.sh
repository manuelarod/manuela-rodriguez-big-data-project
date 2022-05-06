javac -classpath `yarn classpath` -d . WeeklyTripsCountMapper.java
javac -classpath `yarn classpath` -d . WeeklyTripsCountReducer.java
javac -classpath `yarn classpath`:. -d . WeeklyTripsCount.java
jar -cvf weeklyTripsCount.jar *.class
hadoop jar weeklyTripsCount.jar WeeklyTripsCount citibike_data/output/part-r-00000 /user/mr5340/citibike_data/weekly_trips_count_output
