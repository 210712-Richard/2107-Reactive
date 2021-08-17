FROM alpine
RUN touch hello.txt
RUN ls
RUN echo "Hello" >> hello.txt
RUN echo "wombat" >> hello.txt
COPY script.sh .
CMD sh script.sh