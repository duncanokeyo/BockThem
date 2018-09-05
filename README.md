# BockThem
Blocks web browser access to adult rated websites and malicious websites at proxy level

Instructions

First you will have to build and run the project using eclipse or netbeans

1) Open firefox web browser go to Preference > General > Network Proxy
2) Click on settings then select "manual proxy configuration
3) In HTTP Proxy set "local host" in first field
4) In second field set 3018 as port number
5) In SSL Proxy make sure host field is empty and port number is "0" 

you can now browser normally, the proxy will be able to detect malicious websites or adult websites and drop connection..
you can also add more domains that you think the proxy should block
