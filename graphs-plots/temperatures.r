high <- c(58, 62, 66, 69, 74, 79, 82, 82, 80, 74, 64, 58, 71)
low <- c(42, 45, 47, 49, 52, 56, 58, 58, 57, 53, 46, 42, 50)
boxplot(high, low, main = "Average San Jose temperatures each year from 1981 to 2010", ylab = "Temperature °F", names = c("Highs", "Lows"), col = c("Red", "Blue"))
