mydata <- read.csv("../data/loginData.csv")
attach(mydata)
print(mydata)

print("Mean for total login")
print(mean(Logins.Total))
print("Mean for successful login")
print(mean(Logins.success))
print("Mean for Failed login")
print(mean(Logins.Failure))

print("Median for total login")
print(median(Logins.Total))
print("Median for successful login")
print(median(Logins.success))
print("Median for Failed login")
print(median(Logins.Failure))

print(sd(Logins.Total))
print("sd for Total login")
print(sd(Logins.success))
print("sd for Successful login")
print(sd(Logins.Failure))
print("sd for Failed login")

print("Mean for successful login time")
print(mean(Logins.avgSuccess))
print("Mean for failed login time")
print(mean(Logins.avgFail))

print("Median for successful login time")
print(median(Logins.avgSuccess))
print("Median for failed login time")
print(median(Logins.avgFail))

print("sd for successful login time")
print(sd(Logins.avgSuccess))
print("sd for failed login time")
print(sd(Logins.avgFail))


hist(Logins.Total, ylim=c(0,20),breaks=7, main="Histogram of Total Logins", xlab = "Total logins")

hist(Logins.success, ylim=c(0,25), xlim=c(0,25), main="Histogram of Succesful Logins", xlab = "Succesful logins")

hist(Logins.Failure, ylim=c(0,25), xlim=c(0,25), main="Histogram of Failed Logins", xlab = "Failed logins")

hist(Logins.avgFail, ylim=c(0,25), xlim=c(0,60000), main="Histogram of time for Failed Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess, ylim=c(0,25), xlim=c(0,50000), main="Histogram of time for Successful Logins", xlab = "time of Successful logins (ms)")

boxplot(Logins.avgFail, main ="BoxPlot of time for Failed Logins", ylab = "time of Failed logins (ms)")

boxplot(Logins.avgSuccess,main ="BoxPlot of time for Successful Logins", ylab = "time of Successful logins (ms)")

