
// FIRST TRY
const cacheStaticFiles = 'staticFiles';
const staticFiles = [
    '/',
    '/canteensjsonlist',
        'JS/app.js',
        'CSS/style.css',
    ];

//Install event cahcsd alle files und gibt damit den das grundgerüst
self.addEventListener('install', e =>{
    console.log("Serviceworker installed");
    e.waitUntil(
        caches.open(cacheStaticFiles).then(cache => {
                console.log("caching files");
                cache.addAll(staticFiles);
         }).then(() => self.skipWaiting())
    );
});




self.addEventListener('activate', evt =>{
    console.log("Serviceworker activatet")
        evt.waitUntil(
            caches.keys().then(keys => {
                return Promise.all(
                        keys.filter( key => key !== cacheStaticFiles).map(key => caches.delete(key))
                )
            })
        );
    });


self.addEventListener('fetch', evt =>{
evt.respondWith(
    caches.match(evt.request).then(cacheRes => {
        return cacheRes || fetch(evt.request)
        })
    )
})


const limitCacheSize = ( name, size) => (
    caches.open(name).then(cache => {
        cache.keys().then(keys => {
            if (keys.length > size){
                cache.delete(keys[0]).then(limitCacheSize(name, size));
            }
        })
    })
)










