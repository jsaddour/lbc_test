LBC_Test
==

This app is implemented with
- Hilt
- Retrofit 
- Room
- Coroutines/Flow
- LiveData / ViewModel 
- MVVM
- Clean Architecture with feature package
- AssertJ
- Paging 3
- Glide

Notes
==

- Lifecycle and Configuration changes are handled by LiveData/ViemModel.
- As fetched data are quite big, they are paginated locally using android Paging 3 library to insure better performance.
- Glide user-agent is overridden because via.placeholder refuse the original one




