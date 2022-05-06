javac -classpath `yarn classpath` -d . MonthlyRideCountMapper.java
javac -classpath `yarn classpath` -d . MonthlyRideCountReducer.java
javac -classpath `yarn classpath`:. -d . MonthlyRideCount.java
jar -cvf monthlyRideCount.jar *.class
hadoop jar monthlyRideCount.jar MonthlyRideCount citibike_data/output/part-r-00000 /user/mr5340/citibike_data/monthly_ride_count_output
