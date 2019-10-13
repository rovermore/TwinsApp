package com.rovermore.twinsapp.injection

import com.rovermore.twinsapp.profile.ProfilePresenter
import com.rovermore.twinsapp.settings.SettingsPresenter
import com.rovermore.twinsapp.settings.SettingsView
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(settingsView: SettingsView)
    fun inject(settingsPresenter: SettingsPresenter)
    fun inject(profilePresenter: ProfilePresenter)

}