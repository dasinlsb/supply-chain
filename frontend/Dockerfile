FROM node:alpine

WORKDIR /app
COPY package.json ./
COPY yarn.lock ./
RUN yarn

COPY public public
COPY src src
COPY tsconfig.json ./

EXPOSE 3000

RUN yarn start

