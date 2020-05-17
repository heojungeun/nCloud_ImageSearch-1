package com.hackday.imageSearch.ui.photoinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.event.PhotoInfoEvent
import com.hackday.imageSearch.event.PhotoTagEvent
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


class PhotoInfoViewModel (
    private val photoInfoRepository: PhotoInfoRepositoryImpl
) : ViewModel() {

    val disposable: CompositeDisposable = CompositeDisposable()

    var photoOne : MutableLiveData<PhotoInfo> = MutableLiveData()
    val photoInfoList = MutableLiveData<ArrayList<PhotoInfo>>().apply {
        value = ArrayList()
    }
    val photoTagList = MutableLiveData<ArrayList<PhotoTag>>().apply {
        value = ArrayList()
    }

    init {
        subscribePhotoInfoList()
        subscribePhotoTagList()
    }

    fun getAll(){
        disposable.add(
            photoInfoRepository.getAll()
                .subscribe()
        )
    }

    fun deleteAll(){
        disposable.add(
            photoInfoRepository.deleteAll()
                .subscribe()
        )
    }

    fun delete(photoInfo: PhotoInfo){
        disposable.add(
            photoInfoRepository.delete(photoInfo)
                .subscribe()
        )
    }

    fun getPhotoById(id: String){
        disposable.add(
            photoInfoRepository.getPhotoById(id)
                .subscribeWith(object : DisposableSingleObserver<PhotoInfo>(){
                    override fun onSuccess(t: PhotoInfo) {
                        photoOne.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun getPhotoByUri(uri: String){
        disposable.add(
            photoInfoRepository.getPhotoByUri(uri)
                .subscribeWith(object : DisposableSingleObserver<PhotoInfo>(){
                    override fun onSuccess(t: PhotoInfo) {
                        photoOne.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun getPhotoByTag1(tag1: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag1(tag1)
                .subscribe()
        )
    }

    fun getPhotoByTag2(tag2: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag2(tag2)
                .subscribe()
        )
    }

    fun getPhotoByTag3(tag3: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag3(tag3)
                .subscribe()
        )
    }

    fun insertPhoto(photoInfo: PhotoInfo){
        disposable.add(
            photoInfoRepository.insertPhoto(photoInfo)
                .subscribe()
        )
    }

    fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>){
        disposable.add(
            photoInfoRepository.insertPhotoList(photoInfoList)
                .subscribe()
        )
    }

    fun insertTag(photoTag: PhotoTag){
        disposable.add(
            photoInfoRepository.insertTag(photoTag)
                .subscribe()
        )
    }

    fun getAlltag(){
        disposable.add(
            photoInfoRepository.getAllTag()
                .subscribe()
        )
    }

    fun deleteAllTag(){
        disposable.add(
            photoInfoRepository.deleteAllTag()
                .subscribe()
        )
    }

    fun deleteTag(photoTag: PhotoTag){
        disposable.add(
            photoInfoRepository.deleteTag(photoTag)
                .subscribe()
        )
    }

    private fun subscribePhotoInfoList(){
        disposable.add(
            PhotoInfoEvent.addPhotoInfoListSubject.subscribe{
                photoInfoList.value?.add(it)
                photoInfoList.postValue(photoInfoList.value)
            }
        )
    }

    private fun subscribePhotoTagList(){
        disposable.add(
            PhotoTagEvent.addPhotoTagListSubject.subscribe{
                photoTagList.value?.add(it)
                photoTagList.postValue(photoTagList.value)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}