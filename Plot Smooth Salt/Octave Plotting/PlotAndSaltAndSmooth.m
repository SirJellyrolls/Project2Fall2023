
x=0:1:10
y=x.^2

subplot(2,2,1)
plot (x,y)

subplot(2,2,2)
y2=y.+(rand(size(y))*10);
x2=x.+(rand(size(y))*10)
plot(x2,y2)

window=ones(4,1)

yavg=filter(window,1,y2)
xavg=filter(window,1,x2)
subplot(2,2,[3,4])
plot(xavg,yavg)
