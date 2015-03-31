for (x <- 1 to 10) yield x*x

(1 to 10).map((x) => x*x)

for {
  x <- 1 to 10
  if x % 5 == 0
} yield x*x

(1 to 10).filter(_ % 5 == 0).map((x) => x*x)
