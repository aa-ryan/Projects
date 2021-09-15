import re

t = int(input())

while t > 0:
    t -= 1
    n = str(input())
    ans = bool(re.search("^[-+]?[0-9]*\.\d+$", n))
    print(ans)

