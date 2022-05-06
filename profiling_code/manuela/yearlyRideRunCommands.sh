javac -classpath `yarn classpath` -d . YearlyRideCountMapper.java
javac -classpath `yarn classpath` -d . YearlyRideCountReducer.java
javac -classpath `yarn classpath`:. -d . YearlyRideCount.java
jar -cvf yearlyRideCount.jar *.class
hadoop jar yearlyRideCount.jar YearlyRideCount citibike_data/output/part-r-00000 /user/mr5340/citibike_data/yearly_ride_count_output
