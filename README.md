# Transportation in NYC
Team members: Diana Yepes and Manuela Rodriguez

## Dataset: citibike
Citi Bike is a bike share program with stations in New York City and Jersey City. This dataset includes Citi Bike trips since the program launched in September 2013 and is updated daily. For the purpose of this project, we will be using data for 2019, 2020 and 2021.

### Schema
`started_at` STRING — start time, in NYC local time
`ended_at` STRING —stop time, in NYC local time
`start_station_name` STRING
`start__latitude` DECIMAL
`start_longitude` DECIMAL
`end_station_name` STRING
`end_latitude` DECIMAL
`end_longitude` DECIMAL
`user_type` STRING — user type (Customer = 24-hour pass or 7-day pass user, Subscriber = Annual Member)

## Directories and files

### Notes
- To access the output files of the MR jobs the following command can be used: `hdfs dfs -cat home/user/mr5340/citibike_data/output_directory_name/part-r-00000`

- `/ana_code`
    ### Has the use of citibikes vs. taxis changed over the years? Did covid change New Yorkers’ transportation preferences? Did people start using citibikes more than taxis when covid hit?
    - `YearlyAvgDailyTrips.java`, `YearlyAvgDailyTripsMapper.java`, `YearlyAvgDailyTripsReducer.java`: This MapReduce application computes the average number of trips per day per year ( total # of trips / 365 days).
        - `yearlyAvgRunCommands.sh`: commands to run the MR application
        - Output: `citibike_data/yearly_avg_daily_trips_output/part-r-00000`
    - `YearlyAvgWeeklyTrips.java`, `YearlyAvgWeeklyTripsMapper.java`, `YearlyAvgWeeklyTripsReducer.java`: This MapReduce application computes the average number of trips per week per year ( total # of trips / 52 weeks).
        - `yearlyWeeklyAvgRunCommands.sh`: commands to run the MR application
        - Output: `citibike_data/yearly_avg_weekly_trips_output/part-r-00000`
    - `MonthlyAvgDailyTrips.java`, `MonthlyAvgDailyTripsMapper.java`, `MonthlyAvgDailyTripsReducer.java`: This MapReduce application computes the average number of trips per day per month ( total # of trips / 30 days - assuming all months have 30 days).
        - `monthlyAvgRunCommands.sh`: commands to run the MR application
        - Output: `citibike_data/monthly_avg_daily_trips_output/part-r-00000`
    - `MonthlyAvgWeeklyTrips.java`, `MonthlyAvgWeeklyTripsMapper.java`, `MonthlyAvgWeeklyTripsReducer.java`: This MapReduce application computes the average number of trips per week per month ( total # of trips / 4 weeks - assuming all months have 4 weeks).
        - `monthlyWeeklyAvgRunCommands.sh`: commands to run the MR application
        - Output: `citibike_data/monthly_avg_weekly_trips_output/part-r-00000`
    - `2019_to_2020_yoy.csv`, `2020_to_2021_yoy.csv`: Year over year percentage change of avg trips per day (e.g. avg trips per day in 2020 - avg trips per day in 2019 / avg trips per day in 2019). 
        - For this analytic, I created a HIVE table with all the data and ran HiveQL queries to compute the year over year percentage change. First, I created another HIVE table with the average number of daily rides per month. Then, using this table, I computed the year over year percentage change by using a join (see commands and query in `/screenshots`).
        - The hive tables are called `monthly_avg` and `citibike_data` (see `/screenshots`)
    - `monthly_citibike_taxi_ratio.csv`: Ratio of citibike trips to taxi trips per day (# of citibike trips / # of taxi rides * 100). This csv file was generated from a hive table.
    - `citibike_daily_trips.csv`: number of citibike trips per day. This txt file was generated from a hive table.
    - `taxi_daily_trips.csv`: number of citibike trips per day. This csv file was generated from a hive table.
        - For this analytic, two separate hive tables were created (one for citibike and one for taxi) with the total number of rides per day. Then, I joined this table on date to get the ratio. (see `/screenshots`)
        - The hive tables are called `citibike_daily_trips`, `taxi_daily_trips`, `citi_taxi_ratio` 
    ### Is the use of citibikes or taxis seasonal? Is one more popular than the other during certain seasons?
    - `SeasonDailyTrips.java`, `SeasonDailyTripsMapper.java`, `SeasonDailyTripsReducer.java`: This MapReduce application computes the average number of trips per day per season (spring, summer, fall, winter; total # of trips / 90 days – assuming all months have 30 days)
        - `seasonRunCommands.sh`: commands to run the MR application
        - Output: `citibike_data/season_daily_trips_output/part-r-00000`
- `/data_ingest`
    - `data_download.ipynb`: The citibike data had to be downloaded from multiple files (one file per month) from the citibike website. This files contains the code used to download all the files into my local computer.
    - Then, the files were uploaded to peel and a MR job was used to combine them into a single file. (see `etl_code` for details)
    - All the .csv files can be found in `/home/user/mr5340/final_project/data`
- `/etl_code`
    - `/diana`: teammate's subdirectory
    - `/manuela`
        - **Note**: all the output files are in hdfs (`/home/user/mr5340/path_to_file`)
        - `Clean.java`, `CleanMapper.java`, `CleanReducer.java`: We used data from 2019, 2020 and 2021. The data was downloaded as multiple csv files (one for each month). The files from 2019 had a different table schema than the ones from 2020 and 2021, so I designed a common schema (see schema above). This MapReduce job merge the data from all the csv files into a single file, formats the data of each year accoringly and drops any unwanted columns.
            - `cleanRunCommands.sh`: commands to run the MR application
            - Output: `citibike_data/output/part-r-00000`, the output file of this MR job was used as the input file for all other MR applications.
        - `Duration.java`, `DurationMapper.java`, `DurationReducer.java`: This MapReduce application creates a new calculated column with the trip duration in seconds
            - `durationRunCommands.sh`: commands to run the MR application
            - Output: `citibike_data/duration_output/part-r-00000`
- `/profiling_code`
    - `/diana`: teammate's subdirectory
    - `/manuela`
        - **Note**: all the output files are in hdfs (`/home/user/mr5340/path_to_file`)
        - `CountRecords.java`, `CountRecordsMapper.java`,`CountRecordsReducer.java`: This MapReduce job counts the number of records in the combined file
            - `count2RunCommands.sh`: commands to run the MR application
            - Output: `citibike_data/count2_output/part-r-00000`
        - `YearlyRideCount.java`, `YearlyRideCountMapper.java`, `YearlyRideCountReducer.java`: This MapReduce application counts the number of rides per year
            - `yearlyRideRunCommands.sh`: commands to run the MR application
            - Output: `citibike_data/yearly_ride_count_output/part-r-00000`
        - `MonthlyRideCount.java`, `MonthlyRideCountMapper.java`, `MonthlyRideCountReducer.java`: This MapReduce application counts the number of rides per month
            - `monthlyRideRunCommands.sh`: commands to run the MR application
            - Output: `citibike_data/monthly_ride_count_output/part-r-00000`
        - `DayCount.java`, `DayCountMapper.java`, `DayCountReducer.java`: This MapReduce application counts the number of rides per day of the week (Monday, Tuesday, Wedensday, etc.)
            - `dayCountRunCommands.sh`: commands to run the MR application
            - 0utput: `citibike_data/day_count_output/part-r-00000`
- `/screenshots`
    - `/analytics`: screenshot of MR jobs and output listed under `ana_code`
    - `/cleaning`: screenshot of MR jobs and output listed under `etl_code`
    - `/profiling`: screenshot of MR jobs and output listed under `profiling_code`
    - `/hive_queries`: HiveQL queries and commands to export results into csv files
    - `/hive_tables`: Commands to create hive tables and load data from csv and txt files
- `/test_code`
