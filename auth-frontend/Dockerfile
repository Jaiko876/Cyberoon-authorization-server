FROM node:15 as build-deps
WORKDIR /usr/src/app
COPY package.json src ./
ARG NPM_REGISTRY
RUN yarn install --registry ${NPM_REGISTRY}

COPY . ./
RUN yarn build

FROM nginx:1.19.6-alphine
COPY --from=build-deps /usr/src/app/build usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
